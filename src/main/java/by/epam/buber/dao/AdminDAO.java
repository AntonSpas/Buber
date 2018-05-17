package by.epam.buber.dao;

import by.epam.buber.dao.builders.AdminBuilder;
import by.epam.buber.dao.builders.EntityBuilder;
import by.epam.buber.model.Admin;
import by.epam.buber.model.Entity;
import by.epam.buber.util.DAOException;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class AdminDAO extends AbstractDAO {
    private final static String TABLE = "admins";
    private final static String ENTITY_NAME = "Admin";
    private final static String SQL_FIND_BY_LOGIN =
            "SELECT * FROM admins WHERE login=?";

    public AdminDAO(Connection connection) {
        super(connection);
    }

    public Admin getByLogin(String login) throws DAOException {
        List<Admin> admins = executeQuery(SQL_FIND_BY_LOGIN, login);
        try {
            return admins.get(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new DAOException("Admin not found", exception);
        }
        /*Admin admin = null;
        if(!admins.isEmpty()) {
            admin = admins.get(0);
        }
        return admin;*/
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public EntityBuilder getEntityBuilder() {
        return new AdminBuilder();
    }

    @Override
    public List<Object> getFields(Entity entity) {
        Admin admin = (Admin) entity;
        return Arrays.asList(
                admin.getLogin(),
                admin.getPassword());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }
}
