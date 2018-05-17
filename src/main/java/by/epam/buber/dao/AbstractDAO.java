package by.epam.buber.dao;

import by.epam.buber.model.Entity;
import by.epam.buber.dao.builders.EntityBuilder;
import by.epam.buber.util.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> implements DAO<T> {
    private final static String SQL_FIND_ALL = "SELECT * FROM %s";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM %s WHERE id=?";
    private final static String SQL_DELETE = "DELETE * FROM %s WHERE id=?";

    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> findAll() throws DAOException {
        String query = String.format(SQL_FIND_ALL, getTableName());
        return executeQuery(query);
    }

    @Override
    public T findById(int id) throws DAOException {
        String query = String.format(SQL_FIND_BY_ID, getTableName());
        List<T> entities = executeQuery(query, id);
        if (entities.isEmpty()) {
            throw new DAOException(getEntityName() + " not found");
        }
        return entities.get(0);
    }

    @Override
    public boolean delete(int id) throws DAOException {
        String query = String.format(SQL_DELETE, getTableName());
        int result = executeUpdate(query, id);
        return result != 0;
    }

    @Override
    public T save(T entity) throws DAOException {
        if(null == entity.getId()) {
            int id = executeUpdate(getCreateQuery(), getFields(entity));
            entity.setId(id);
        } else {
            executeUpdate(getUpdateQuery(), getFields(entity), entity.getId());
        }
        return entity;
    }

    protected List<T> executeQuery(String sql, Object... params) throws DAOException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = prepareStatement(sql, params);
                ResultSet resultSet = statement.executeQuery()) {
            T entity;
            while (resultSet.next()) {
                EntityBuilder<T> entityBuilder = getEntityBuilder();
                entity = entityBuilder.build(resultSet);
                entities.add(entity);
            }
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
        return entities;
    }

    protected int executeUpdate(String sql, Object... params) throws DAOException {
        try (PreparedStatement statement = prepareStatement(sql, params)) {
            int result = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else  {
                return result;
            }
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    protected PreparedStatement prepareStatement(String sql, Object... params) throws DAOException {
        List<Object> allParameters = new ArrayList<>();
        for (int i=0; i<params.length; i++) {
            if(params[i] instanceof List) {
                List<Object> parameters = (List<Object>) params[i];
                allParameters.addAll(parameters);
            } else {
                allParameters.add(params[i]);
            }
        }
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i=0; i<allParameters.size(); i++) {
                statement.setObject(i+1, allParameters.get(i));
            }
            return statement;
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    protected abstract String getTableName();
    protected abstract String getCreateQuery();
    protected abstract String getUpdateQuery();
    protected abstract EntityBuilder getEntityBuilder();
    protected abstract List<Object> getFields(Entity entity);
    protected abstract String getEntityName();
}
