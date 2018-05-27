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
import java.util.Optional;

public class DriverServiceImpl implements DriverService {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Driver login(String email, String password) throws ServiceException {
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DriverDAO dao = new DriverDAO(connection);
            Optional<Driver> driverOptional = Optional.ofNullable(dao.findByEmail(email));
            Driver driver = driverOptional.orElseThrow(() ->
                    new ServiceException("Driver not found for email " + email));
            password = DigestUtils.sha1Hex(password);
            if(password.equals(driver.getPassword())) {
                return driver;
            } else {
                throw new ServiceException("Wrong password for driver's email " + email);
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void checkPresence(String email) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DriverDAO dao = new DriverDAO(connection);
            Driver driver = dao.findByEmail(email);
            if (driver != null) {
                throw new ServiceException("Driver with " + email + " already present");
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
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

    public Driver getById(Integer id) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<Driver> dao = new DriverDAO(connection);
            Optional<Driver> driver = Optional.ofNullable(dao.findById(id));
            return driver.orElseThrow(() ->
                    new ServiceException("Driver " + id + " not found"));
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
