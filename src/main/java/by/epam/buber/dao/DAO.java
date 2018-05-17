package by.epam.buber.dao;

import by.epam.buber.model.Entity;
import by.epam.buber.util.DAOException;

import java.util.List;

public interface DAO<T extends Entity> {
    List<T> findAll() throws DAOException;
    T findById(int id) throws DAOException;
    boolean delete(int id) throws DAOException;
    T save(T entity) throws DAOException;

    /*
    boolean delete(T entity);
    boolean create(T entity) throws DAOException;*/
}
