package by.epam.buber.service;

import by.epam.buber.model.Driver;
import by.epam.buber.util.ServiceException;

public interface DriverService {

    /**
     * Authenticates driver
     *
     * @param email driver email
     * @param password driver password
     * @throws ServiceException if driver not found or wrong password provided
     * or any other exceptions occurs
     */
    Driver login(String email, String password) throws ServiceException;

    /**
     * Saves driver to underlying data storage
     *
     * @param driver driver to save
     * @throws ServiceException if any exceptions occurs
     */
    Driver save(Driver driver) throws ServiceException;

    /**
     * Returns driver by identifier
     *
     * @param id driver identifier
     * @return founded driver
     * @throws ServiceException if driver not found or any other exceptions occurs
     */
    Driver getById(Integer id) throws ServiceException;
}
