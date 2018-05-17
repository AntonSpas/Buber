package by.epam.buber.controller.command.driver;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.validators.LoginValidator;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.model.Driver;
import by.epam.buber.model.enums.UserType;
import by.epam.buber.service.DriverService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class DriverLoginCommand implements Command {
    private static final String DRIVER_LOGIN = "/driver-login";
    private static final String AVAILABLE_ORDERS = "/driver/available-orders";
    private static final String VALIDATION_LOG = "Driver login form is not valid";
    private static final String LOGGED_IN_LOG = "Driver %d successfully logged in";

    private static final Logger logger = LoggerFactory.getLogger(DriverLoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        DriverService service = new DriverService();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<String> fields = Arrays.asList(email, password);
        LoginValidator validator = new LoginValidator();
        if (!validator.isValid(fields, request)) {
            logger.warn(VALIDATION_LOG);
            return new CommandResult(DRIVER_LOGIN, Action.REDIRECT);
        }
        Driver driver = service.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("driver_id", driver.getId());
        session.setAttribute("car_type", driver.getCarType());
        session.setAttribute("driver_name", driver.getName());
        session.setAttribute("userType", UserType.DRIVER);
        logger.info(String.format(LOGGED_IN_LOG, driver.getId()));
        return new CommandResult(AVAILABLE_ORDERS, Action.REDIRECT);
    }
}
