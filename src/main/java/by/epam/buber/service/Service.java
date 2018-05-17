package by.epam.buber.service;

import by.epam.buber.model.Entity;
import by.epam.buber.util.ServiceException;

import java.util.List;

public interface Service<T extends Entity> {
    List<T> findAll() throws ServiceException;
    T findById(int id) throws ServiceException;
    boolean delete(int id) throws ServiceException;
    T save(T entity) throws ServiceException;
}
