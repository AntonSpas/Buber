package by.epam.buber.service;

import by.epam.buber.model.Client;
import by.epam.buber.util.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    Client login(String email, String password) throws ServiceException;
    Client save(Client client) throws ServiceException;
    void replenishAccount(Integer id, BigDecimal amount) throws ServiceException;
    void banClient(Integer id) throws ServiceException;
    List<Client> getAll() throws ServiceException;
    Client findById(Integer id) throws ServiceException;
}
