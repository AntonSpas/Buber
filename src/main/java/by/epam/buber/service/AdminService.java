package by.epam.buber.service;

import by.epam.buber.model.Admin;
import by.epam.buber.util.ServiceException;

public interface AdminService {
    Admin login(String login, String password) throws ServiceException;
}
