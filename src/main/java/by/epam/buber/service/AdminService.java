package by.epam.buber.service;

import by.epam.buber.model.Admin;
import by.epam.buber.util.ServiceException;

public interface AdminService {

    /**
     * Authenticates admin
     *
     * @param login admin login
     * @param password admin password
     * @throws ServiceException if admin not found or wrong password provided
     * or any other exceptions occurs
     */
    Admin login(String login, String password) throws ServiceException;
}
