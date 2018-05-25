package by.epam.buber.controller.command.admin;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.validators.AdminLoginValidator;
import by.epam.buber.controller.validators.Validator;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.model.Admin;
import by.epam.buber.model.enums.UserType;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.Impl.AdminServiceImpl;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class AdminLoginCommand implements Command {
    private static final String ADMIN_LOGIN = "/admin-login";
    private static final String ADMIN_HOME = "/admin/administration";
    private static final String ERROR = "/WEB-INF/views/error.jsp";
    private static final String ADMIN_LOGIN_FAULT_MESSAGE = "admin_login_fault_error";
    private static final String VALIDATION_LOG = "Admin login form is not valid";
    private static final String LOGGED_IN_LOG = "Admin %d successfully logged in";

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        AdminService service = new AdminServiceImpl();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        List<String> fields = Arrays.asList(login, password);
        Validator validator = new AdminLoginValidator();
        if (!validator.isValid(fields, request)) {
            logger.warn(VALIDATION_LOG);
            return new CommandResult(ADMIN_LOGIN, Action.REDIRECT);
        }
        Admin admin;
        try {
            admin = service.login(login, password);
        } catch (ServiceException exception) {
            logger.warn(exception.getMessage(), exception);
            request.setAttribute("error", ADMIN_LOGIN_FAULT_MESSAGE);
            return new CommandResult(ERROR, Action.FORWARD);
        }
        HttpSession session = request.getSession();
        session.setAttribute("admin_id", admin.getId());
        session.setAttribute("userType", UserType.ADMIN);
        logger.info(String.format(LOGGED_IN_LOG, admin.getId()));
        return new CommandResult(ADMIN_HOME, Action.REDIRECT);
    }
}
