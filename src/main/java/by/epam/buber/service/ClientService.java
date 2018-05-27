package by.epam.buber.service;

import by.epam.buber.model.Client;
import by.epam.buber.util.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    /**
     * Authenticates client
     *
     * @param email client email
     * @param password client password
     * @throws ServiceException if client not found or wrong password provided
     * or any other exceptions occurs
     */
    Client login(String email, String password) throws ServiceException;

    /**
     * Checks if client already present
     *
     * @param email client email
     * @throws ServiceException if client already present or any other exceptions occurs
     */
    void checkPresence(String email) throws ServiceException;

    /**
     * Saves client to underlying data storage
     *
     * @param client client to save
     * @throws ServiceException if any exceptions occurs
     */
    Client save(Client client) throws ServiceException;

    /**
     * Replenishes client account
     *
     * @param id client identifier
     * @param amount amount of money
     * @throws ServiceException if any exceptions occurs
     */
    void replenishAccount(Integer id, BigDecimal amount) throws ServiceException;

    /**
     * Banes client
     *
     * @param id client identifier
     * @throws ServiceException if any exceptions occurs
     */
    void banClient(Integer id) throws ServiceException;

    /**
     * Returns client by identifier
     *
     * @param id client identifier
     * @return founded client
     * @throws ServiceException if client not found or any other exceptions occurs
     */
    Client getById(Integer id) throws ServiceException;

    /**
     * Returns clients by page
     *
     * @param firstRow first row identifier
     * @param rowCount number of rows
     * @return list of clients
     * @throws ServiceException if any exceptions occurs
     */
    List<Client> getClientsByPage(int firstRow, int rowCount)
            throws ServiceException;

    /**
     * Returns number of clients
     *
     * @return number of clients
     * @throws ServiceException if any exceptions occurs
     */
    Integer getRecordsQuantity() throws ServiceException;
}
