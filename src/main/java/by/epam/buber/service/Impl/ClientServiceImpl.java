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
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Client login(String email, String password) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            ClientDAO dao = new ClientDAO(connection);
            Optional<Client> clientOptional = Optional.ofNullable(dao.findByEmail(email));
            Client client = clientOptional.orElseThrow(() ->
                    new ServiceException("Client not found for email " + email));
            password = DigestUtils.sha1Hex(password);
            if(password.equals(client.getPassword())) {
                return client;
            } else {
                throw new ServiceException("Wrong password for client's email " + email);
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void checkPresence(String email) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            ClientDAO dao = new ClientDAO(connection);
            Client client = dao.findByEmail(email);
            if (client != null) {
                throw new ServiceException("Client with " + email + " already present");
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
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

    public Client getById(Integer id) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<Client> dao = new ClientDAO(connection);
            Optional<Client> client = Optional.ofNullable(dao.findById(id));
            return client.orElseThrow(() ->
                    new ServiceException("Client " + id + " not found"));
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<Client> getClientsByPage(int firstRow, int rowCount)
            throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            ClientDAO dao = new ClientDAO(connection);
            return dao.findByPage(firstRow, rowCount);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Integer getRecordsQuantity() throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            ClientDAO dao = new ClientDAO(connection);
            return dao.findMaxId();
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
