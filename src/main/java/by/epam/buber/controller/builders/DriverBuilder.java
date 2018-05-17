package by.epam.buber.controller.builders;

import by.epam.buber.model.Driver;
import by.epam.buber.model.enums.CarType;

import javax.servlet.http.HttpServletRequest;

public class DriverBuilder implements EntityBuilder {

    @Override
    public Driver build(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String carTypeParameter = request.getParameter("car_type");
        CarType carType = CarType.valueOf(carTypeParameter.toUpperCase());
        String carModel = request.getParameter("car_model");
        String carNumber = request.getParameter("car_number");

        Driver driver = new Driver();

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
