package by.epam.buber.dao.builders;

import by.epam.buber.model.Driver;
import by.epam.buber.model.enums.CarType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverBuilder implements EntityBuilder {

    @Override
    public Driver build(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        String email = resultSet.getString(4);
        String password = resultSet.getString(5);
        String phone = resultSet.getString(6);
        String stringCarType = resultSet.getString(7);
        CarType carType = CarType.valueOf(stringCarType);
        String carModel = resultSet.getString(8);
        String carNumber = resultSet.getString(9);

        Driver driver = new Driver();

        driver.setId(id);
        driver.setName(name);
        driver.setSurname(surname);
        driver.setEmail(email);
        driver.setPassword(password);
        driver.setPhone(phone);
        driver.setCarType(carType);
        driver.setCarModel(carModel);
        driver.setCarNumber(carNumber);

        return driver;
    }
}
