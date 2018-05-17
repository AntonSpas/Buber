package by.epam.buber.dao.builders;

import by.epam.buber.model.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityBuilder<T extends Entity> {
    T build (ResultSet resultSet) throws SQLException;
}
