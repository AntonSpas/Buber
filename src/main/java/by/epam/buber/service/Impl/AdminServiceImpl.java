package by.epam.buber.service.Impl;

import by.epam.buber.dao.pool.ConnectionPool;
import by.epam.buber.dao.pool.ConnectionWrapper;
import by.epam.buber.dao.AdminDAO;
import by.epam.buber.model.Admin;
import by.epam.buber.service.AdminService;
import by.epam.buber.util.DAOException;
import by.epam.buber.util.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Admin login(String login, String password) throws ServiceException {
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            AdminDAO dao = new AdminDAO(connection);
            Optional<Admin> adminOptional = Optional.ofNullable(dao.findByLogin(login));
            Admin admin = adminOptional.orElseThrow(() ->
                    new ServiceException("Admin " + login + " not found"));
            password = DigestUtils.sha1Hex(password);
            if(password.equals(admin.getPassword())) {
                return admin;
            } else {
                throw new ServiceException("Wrong password for " + login + " admin");
            }
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
