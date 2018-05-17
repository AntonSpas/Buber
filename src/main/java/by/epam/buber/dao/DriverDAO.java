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
    private final static String ENTITY_NAME = "Driver";
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
//    private final static String SQL_FIND_BY_ID = "SELECT * FROM drivers WHERE id=?";

    public DriverDAO(Connection connection) {
        super(connection);
    }

    /*@Override
    public Driver findById(int id) throws DAOException {
        List<Driver> drivers = executeQuery(SQL_FIND_BY_ID, id);
        Driver driver = null;
        if(!drivers.isEmpty()) {
            driver = drivers.get(0);
        }
        return driver;
    }*/

    public void putMoney(Integer id, BigDecimal cost) throws DAOException {
        executeUpdate(SQL_ADD_MONEY, cost, id);
    }

    public Driver getByEmail(String email) throws DAOException {
        List<Driver> drivers = executeQuery(SQL_FIND_BY_EMAIL, email);
        try {
            return drivers.get(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new DAOException("Driver not found", exception);
        }
        /*Driver driver = null;
        if(!drivers.isEmpty()) {
            driver = drivers.get(0);
        }
        return driver;*/
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

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }
}
