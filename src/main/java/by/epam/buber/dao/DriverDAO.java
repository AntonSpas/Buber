package by.epam.buber.dao;

import by.epam.buber.dao.builders.DriverBuilder;
import by.epam.buber.dao.builders.EntityBuilder;
import by.epam.buber.model.Driver;
import by.epam.buber.model.Entity;
import by.epam.buber.util.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class DriverDAO extends AbstractDAO {
    private final static String TABLE = "drivers";
    private final static String SQL_INSERT =
            "INSERT INTO drivers(name, surname, email, password, phone, car_type," +
                    " car_model, car_number) VALUES(?,?,?,SHA1(?),?,?,?,?)";
    private final static String SQL_UPDATE =
            "UPDATE drivers SET name=?, surname=?, email=?, password=SHA1(?), " +
                    "phone=?, car_type=?, car_model=?, car_number=? WHERE id=?";
    private final static String SQL_FIND_BY_EMAIL =
            "SELECT * FROM drivers WHERE email=?";
    private final static String SQL_ADD_MONEY =
            "UPDATE drivers SET earned = earned + ? WHERE id=?";

    public DriverDAO(Connection connection) {
        super(connection);
    }

    /**
     * Puts amount of money to driver account
     *
     * @param id driver identifier
     * @param amount amount of money
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public void putMoney(Integer id, BigDecimal amount) throws DAOException {
        executeUpdate(SQL_ADD_MONEY, amount, id);
    }

    /**
     * Finds driver by email
     *
     * @param email driver email
     * @return founded driver or null if there is none
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public Driver getByEmail(String email) throws DAOException {
        List<Driver> drivers = executeQuery(SQL_FIND_BY_EMAIL, email);
        if(drivers.isEmpty()) {
            return null;
        }
        return drivers.get(0);
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public String getCreateQuery() {
        return SQL_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE;
    }

    @Override
    public EntityBuilder getEntityBuilder() {
        return new DriverBuilder();
    }

    @Override
    public List<Object> getFields(Entity entity) {
        Driver driver = (Driver) entity;
        return Arrays.asList(
                driver.getName(),
                driver.getSurname(),
                driver.getEmail(),
                driver.getPassword(),
                driver.getPhone(),
                driver.getCarType().toString(),
                driver.getCarModel(),
                driver.getCarNumber());
    }
}
