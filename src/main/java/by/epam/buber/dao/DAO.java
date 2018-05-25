package by.epam.buber.dao;

import by.epam.buber.model.Entity;
import by.epam.buber.util.DAOException;

import java.util.List;

public interface DAO<T extends Entity> {

    /**
     * Finds all entities from the database
     *
     * @return list of all entities
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    List<T> findAll() throws DAOException;

    /**
     * Finds entity by identifier
     *
     * @param id entity identifier
     * @return founded entity or null if there is none
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    T findById(int id) throws DAOException;

    /**
     * Deletes entity by identifier
     *
     * @param id entity identifier
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    boolean delete(int id) throws DAOException;

    /**
     * Saves entity to the database
     *
     * @param entity to save
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    T save(T entity) throws DAOException;
}
