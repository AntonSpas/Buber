package by.epam.buber.dao.builders;

import by.epam.buber.model.Admin;
import by.epam.buber.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBuilder implements EntityBuilder {
    @Override
    public Entity build(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String login = resultSet.getString(2);
        String password = resultSet.getString(3);

        Admin admin = new Admin();

        admin.setId(id);
        admin.setLogin(login);
        admin.setPassword(password);

        return admin;
    }
}
