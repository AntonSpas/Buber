package by.epam.buber.dao;

import by.epam.buber.model.Client;
import by.epam.buber.model.Entity;
import by.epam.buber.dao.builders.ClientBuilder;
import by.epam.buber.dao.builders.EntityBuilder;
import by.epam.buber.util.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class ClientDAO extends AbstractDAO {
    private final static String TABLE = "clients";
    private final static String ENTITY_NAME = "Client";
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
//    private final static String SQL_FIND_BY_ID = "SELECT * FROM clients WHERE id=?";

    public ClientDAO(Connection connection) {
        super(connection);
    }

    /*@Override
    public Client findById(int id) throws DAOException {
        List<Client> clients = executeQuery(SQL_FIND_BY_ID, id);
        Client client = null;
        if(!clients.isEmpty()) {
            client = clients.get(0);
        }
        return client;
    }*/

    public void addBanScore(Integer id) throws DAOException {
        executeUpdate(SQL_ADD_BAN_SCORE, id);
    }

    public void addMoney(Integer id, BigDecimal amount) throws DAOException {
        executeUpdate(SQL_ADD_MONEY, amount, id);
    }

    public void banClient(Integer id) throws DAOException {
        executeUpdate(SQL_BAN_CLIENT, id);
    }

    public void takeMoney(Integer id, BigDecimal cost) throws DAOException {
        executeUpdate(SQL_TAKE_MONEY, cost, id);
    }

    public Client getByEmail(String email) throws DAOException {
            List<Client> clients = executeQuery(SQL_FIND_BY_EMAIL, email);
            try {
                return clients.get(0);
            } catch (IndexOutOfBoundsException exception) {
                throw new DAOException("Client not found", exception);
            }
            /*Client client = null;
            if(!clients.isEmpty()) {
                client = clients.get(0);
            }
            return client;*/
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

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }
}
