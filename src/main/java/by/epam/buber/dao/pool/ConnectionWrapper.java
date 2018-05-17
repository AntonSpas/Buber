package by.epam.buber.dao.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionWrapper implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionWrapper.class);
    private ProxyConnection connection;

    public ConnectionWrapper(ProxyConnection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            logger.error("SQLException while rollback call", exception);
            throw new IllegalStateException("SQLException while rollback call", exception);
        }
    }

    @Override
    public void close() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.returnConnection(connection);
    }
}
