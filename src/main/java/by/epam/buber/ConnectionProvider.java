package by.epam.buber;

import java.sql.Connection;

public class ConnectionProvider implements AutoCloseable {
    private Connection connection;

    public ConnectionProvider(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.returnConnection(connection);
    }
}
