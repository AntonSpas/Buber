package by.epam.buber.controller.command.admin;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.builders.DriverBuilder;
import by.epam.buber.controller.builders.EntityBuilder;
import by.epam.buber.model.Driver;
import by.epam.buber.service.DriverService;
import by.epam.buber.util.ServiceException;
import by.epam.buber.util.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarRegistrationCommand implements Command {
    private static final String CAR_REGISTRATION = "/admin/register-car";
    private static final String ADMIN_HOME = "/admin/administration";

    private static final Logger logger = LoggerFactory.getLogger(CarRegistrationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        EntityBuilder entityBuilder = new DriverBuilder();
        Driver driver;
        try {
            driver = (Driver) entityBuilder.build(request);
        } catch (ValidationException exception) {
            logger.warn(exception.getMessage(), exception);
            return new CommandResult(CAR_REGISTRATION, Action.REDIRECT);
        }
        DriverService service = new DriverService();
        service.save(driver);
        return new CommandResult(ADMIN_HOME, Action.REDIRECT);
    }
}
