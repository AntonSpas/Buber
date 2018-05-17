package by.epam.buber.controller.command.admin;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.builders.DriverBuilder;
import by.epam.buber.controller.builders.EntityBuilder;
import by.epam.buber.controller.validators.DriverSignUpValidator;
import by.epam.buber.controller.validators.Validator;
import by.epam.buber.model.Driver;
import by.epam.buber.service.DriverService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class DriverRegistrationCommand implements Command {
    private static final String CAR_REGISTRATION = "/admin/register-car";
    private static final String ADMIN_HOME = "/admin/administration";
    private static final String VALIDATION_LOG = "Driver registration form is not valid";
    private static final String REGISTERED_LOG =
            "Driver %d was successfully registered by admin %d";

    private static final Logger logger = LoggerFactory.getLogger(DriverRegistrationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        EntityBuilder entityBuilder = new DriverBuilder();
        Driver driver = (Driver) entityBuilder.build(request);

        List<String> fields = Arrays.asList(
                driver.getName(),
                driver.getSurname(),
                driver.getEmail(),
                driver.getPassword(),
                driver.getPhone(),
                driver.getCarType().toString(),
                driver.getCarModel(),
                driver.getCarNumber());
        Validator validator = new DriverSignUpValidator();
        if (!validator.isValid(fields, request)) {
            logger.warn(VALIDATION_LOG);
            return new CommandResult(CAR_REGISTRATION, Action.REDIRECT);
        }
        DriverService service = new DriverService();
        service.save(driver);
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("admin_id");
        logger.info(String.format(REGISTERED_LOG, driver.getId(), adminId));
        return new CommandResult(ADMIN_HOME, Action.REDIRECT);
    }
}
