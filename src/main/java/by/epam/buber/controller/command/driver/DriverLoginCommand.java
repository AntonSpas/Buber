package by.epam.buber.controller.command.driver;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.validators.LoginValidator;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.model.Driver;
import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.UserType;
import by.epam.buber.service.DriverService;
import by.epam.buber.service.Impl.DriverServiceImpl;
import by.epam.buber.service.Impl.OrderServiceImpl;
import by.epam.buber.service.OrderService;
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
    private static final String ERROR = "/error";
    private static final String LOGIN_FAULT_MESSAGE = "login_fault_error";
    private static final String LOGGED_IN_LOG = "Driver %d successfully logged in";

    private static final Logger logger = LoggerFactory.getLogger(DriverLoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        DriverService service = new DriverServiceImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<String> fields = Arrays.asList(email, password);
        LoginValidator validator = new LoginValidator();
        if (!validator.isValid(fields, request)) {
            logger.warn("Driver login form is not valid");
            return new CommandResult(DRIVER_LOGIN, Action.REDIRECT);
        }
        Driver driver;
        HttpSession session = request.getSession();
        try {
            driver = service.login(email, password);
        } catch (ServiceException exception) {
            logger.warn(exception.getMessage(), exception);
            session.setAttribute("error", LOGIN_FAULT_MESSAGE);
            return new CommandResult(ERROR, Action.REDIRECT);
        }
        session.setAttribute("driver_id", driver.getId());
        session.setAttribute("car_type", driver.getCarType());
        session.setAttribute("driver_name", driver.getName());
        session.setAttribute("userType", UserType.DRIVER);
        logger.info(String.format(LOGGED_IN_LOG, driver.getId()));

        OrderService orderService = new OrderServiceImpl();
        List<RideOrder> orders = orderService.getUnconfirmedOrders(driver.getId());
        if (!orders.isEmpty()) {
            session.setAttribute("unconfirmed_present", true);
            session.setAttribute("unconfirmed_orders", orders);
        }

        return new CommandResult(AVAILABLE_ORDERS, Action.REDIRECT);
    }
}
