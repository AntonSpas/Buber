package by.epam.buber.service.Impl;

import by.epam.buber.dao.pool.ConnectionPool;
import by.epam.buber.dao.pool.ConnectionWrapper;
import by.epam.buber.dao.DAO;
import by.epam.buber.dao.DriverDAO;
import by.epam.buber.model.Driver;
import by.epam.buber.service.DriverService;
import by.epam.buber.util.DAOException;
import by.epam.buber.util.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;

public class DriverServiceImpl implements DriverService {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Driver login(String email, String password) throws ServiceException {
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DriverDAO dao = new DriverDAO(connection);
            Driver driver = dao.getByEmail(email);
            password = DigestUtils.sha1Hex(password);
            if(password.equals(driver.getPassword())) {
                return driver;
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
        return null;
    }

    public Driver save(Driver driver) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<Driver> dao = new DriverDAO(connection);
            return dao.save(driver);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Driver findById (Integer id) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<Driver> dao = new DriverDAO(connection);
            return dao.findById(id);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
