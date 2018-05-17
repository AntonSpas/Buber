package by.epam.buber.dao.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final String CREATED_LOG = "Connection Pool was successfully created";
    private static final String CREATION_FAULT_LOG = "Connection Pool creation fault";
    private static final String TAKING_CONNECTION_LOG =
            "Taking connection from Connection Pool fault";

    private static ConnectionPool instance;
    private static LinkedList<ProxyConnection> connections = new LinkedList<>();
    private static AtomicBoolean isCreated = new AtomicBoolean();
    private static ReentrantLock lock = new ReentrantLock();

    private static Semaphore semaphore;

    private static int poolSize;

    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private static void init() {
        Connection connection;
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String driver = resource.getString("database.driver");
            String url = resource.getString("database.url");
            String user = resource.getString("database.user");
            String password = resource.getString("database.password");
            String useUnicode = resource.getString("database.useUnicode");
            String encoding = resource.getString("database.encoding");
            String autoReconnect = resource.getString("database.autoReconnect");
            String useSSL = resource.getString("database.useSSL");
            String serverTimezone = resource.getString("serverTimezone");

            Properties properties = new Properties();
            properties.put("user", user);
            properties.put("password", password);
            properties.put("useUnicode", useUnicode);
            properties.put("encoding", encoding);
            properties.put("autoReconnect", autoReconnect);
            properties.put("useSSL", useSSL);
            properties.put("serverTimezone", serverTimezone);
            Class.forName(driver);

            String poolSizeProperty = resource.getString("poolsize");
            poolSize = Integer.parseInt(poolSizeProperty);

            semaphore = new Semaphore(poolSize, true);

            for (int i = 0; i < poolSize; i++) {
                connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                connections.add(proxyConnection);
            }
            logger.info(CREATED_LOG);

        } catch (ClassNotFoundException | SQLException exception) {
            logger.error(CREATION_FAULT_LOG, exception);
            throw new IllegalStateException(CREATION_FAULT_LOG, exception);
        }
    }

    public ProxyConnection takeConnection() {
        ProxyConnection connection = null;
            lock.lock();
            try {
                semaphore.acquire();
                connection = connections.remove();
            } catch (InterruptedException exception) {
                logger.error(TAKING_CONNECTION_LOG, exception);
                throw new IllegalStateException(TAKING_CONNECTION_LOG, exception);
            } finally {
                lock.unlock();
            }
        return connection;
    }

    public void returnConnection(ProxyConnection connection) {
        lock.lock();
        try {
            connections.add(connection);
            semaphore.release();
        }
        finally {
            lock.unlock();
        }
    }

    public static ConnectionPool getInstance() {
        if(!isCreated.get() && instance == null) {
            lock.lock();
            try {
                if(instance == null) {
                    instance = new ConnectionPool();
                    init();
                    isCreated.set(true);
                }
            }
            finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    protected void finalize() throws Throwable {
        for (Connection connection: connections) {
            connection.close();
        }
        super.finalize();
    }

    private ConnectionPool() {
    }
}
