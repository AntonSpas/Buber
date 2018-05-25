package by.epam.buber.dao;

import by.epam.buber.model.Client;
import by.epam.buber.model.Entity;
import by.epam.buber.dao.builders.ClientBuilder;
import by.epam.buber.dao.builders.EntityBuilder;
import by.epam.buber.util.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class ClientDAO extends AbstractDAO {
    private final static String TABLE = "clients";
    private final static String SQL_INSERT =
            "INSERT INTO clients(name, surname, email, password, phone) " +
                    "VALUES(?,?,?,SHA1(?),?)";
    private final static String SQL_UPDATE =
            "UPDATE clients SET name=?, surname=?, email=?, password=SHA1(?), " +
                    "phone=?, ban_scores=?, enabled=? WHERE id=?";
    private final static String SQL_FIND_BY_EMAIL =
            "SELECT * FROM clients WHERE email=?";
    private final static String SQL_TAKE_MONEY =
            "UPDATE clients SET account = account - ? WHERE id=?";
    private final static String SQL_ADD_MONEY =
            "UPDATE clients SET account = account + ? WHERE id=?";
    private final static String SQL_ADD_BAN_SCORE =
            "UPDATE clients SET ban_scores = ban_scores + 1 WHERE id=?";
    private final static String SQL_BAN_CLIENT =
            "UPDATE clients SET enabled = 0 WHERE id=?";
    private final static String SQL_FIND_BY_PAGE =
            "SELECT * FROM clients ORDER BY id LIMIT ? OFFSET ?";
    private final static String SQL_MAX_ID = "SELECT MAX(id) FROM clients";

    public ClientDAO(Connection connection) {
        super(connection);
    }

    /**
     * Finds clients by page from the database
     *
     * @param firstRow first row identifier
     * @param rowCount number of rows
     * @return list of clients
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public List<Client> findByPage(int firstRow, int rowCount) throws DAOException {
        return executeQuery(SQL_FIND_BY_PAGE, rowCount, firstRow);
    }

    /**
     * Finds last row identifier from clients table
     *
     * @return founded client identifier
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public Integer findMaxId() throws DAOException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_MAX_ID)) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    /**
     * Adds ban score to client
     *
     * @param id client identifier
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public void addBanScore(Integer id) throws DAOException {
        executeUpdate(SQL_ADD_BAN_SCORE, id);
    }

    /**
     * Adds amount of money to client account
     *
     * @param id client identifier
     * @param amount amount of money
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public void addMoney(Integer id, BigDecimal amount) throws DAOException {
        executeUpdate(SQL_ADD_MONEY, amount, id);
    }

    /**
     * Banes client
     *
     * @param id client identifier
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public void banClient(Integer id) throws DAOException {
        executeUpdate(SQL_BAN_CLIENT, id);
    }

    /**
     * Takes amount of money from client account
     *
     * @param id client identifier
     * @param amount amount of money
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public void takeMoney(Integer id, BigDecimal amount) throws DAOException {
        executeUpdate(SQL_TAKE_MONEY, amount, id);
    }

    /**
     * Finds client by email
     *
     * @param email client email
     * @return founded client or null if there is none
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public Client findByEmail(String email) throws DAOException {
            List<Client> clients = executeQuery(SQL_FIND_BY_EMAIL, email);
            if(clients.isEmpty()) {
                return null;
            }
            return clients.get(0);
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public String getCreateQuery() {
        return SQL_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE;
    }

    @Override
    public EntityBuilder getEntityBuilder() {
        return new ClientBuilder();
    }

    @Override
    public List<Object> getFields(Entity entity) {
        Client client = (Client) entity;
        return Arrays.asList(
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPassword(),
                client.getPhone());
    }
}
