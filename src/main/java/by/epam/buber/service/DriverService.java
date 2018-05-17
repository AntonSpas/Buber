package by.epam.buber.service;

import by.epam.buber.model.Driver;
import by.epam.buber.util.ServiceException;

public interface DriverService {
    Driver login(String email, String password) throws ServiceException;
    Driver save(Driver driver) throws ServiceException;
    Driver findById (Integer id) throws ServiceException;
}
