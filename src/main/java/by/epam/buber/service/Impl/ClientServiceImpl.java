package by.epam.buber.service.Impl;

import by.epam.buber.dao.pool.ConnectionPool;
import by.epam.buber.dao.pool.ConnectionWrapper;
import by.epam.buber.dao.ClientDAO;
import by.epam.buber.dao.DAO;
import by.epam.buber.model.Client;
import by.epam.buber.service.ClientService;
import by.epam.buber.util.DAOException;
import by.epam.buber.util.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Client login(String email, String password) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            ClientDAO dao = new ClientDAO(connection);
            Client client = dao.getByEmail(email);
            password = DigestUtils.sha1Hex(password);
            if(password.equals(client.getPassword())) {
                return client;
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
        return null;
    }

    public Client save(Client client) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<Client> dao = new ClientDAO(connection);
            client = dao.save(client);
            return client;
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void replenishAccount(Integer id, BigDecimal amount) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            ClientDAO dao = new ClientDAO(connection);
            dao.addMoney(id, amount);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void banClient(Integer id) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            ClientDAO dao = new ClientDAO(connection);
            dao.banClient(id);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<Client> getAll() throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<Client> dao = new ClientDAO(connection);
            List<Client> clients = dao.findAll();
            return clients;
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Client findById(Integer id) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<Client> dao = new ClientDAO(connection);
            Client client = dao.findById(id);
            return client;
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
