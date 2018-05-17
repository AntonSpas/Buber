package by.epam.buber.controller.command.client;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.validators.LoginValidator;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.model.Client;
import by.epam.buber.model.enums.UserType;
import by.epam.buber.service.ClientService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class ClientLoginCommand implements Command {
    private static final String CLIENT_LOGIN = "/client-login";
    private static final String CLIENT_HOME = "/client/client-home";
    private static final String ERROR = "/WEB-INF/views/error.jsp";
    private static final String ERROR_MESSAGE = "banned_error";
    private static final String VALIDATION_LOG = "Client login form is not valid";
    private static final String BANNED_LOG = "Client %d tried to login, but he is banned";
    private static final String LOGGED_IN_LOG = "Client %d successfully logged in";

    private static final Logger logger = LoggerFactory.getLogger(ClientLoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        ClientService service = new ClientService();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<String> fields = Arrays.asList(email, password);
        LoginValidator validator = new LoginValidator();
        if (!validator.isValid(fields, request)) {
            logger.warn(VALIDATION_LOG);
            return new CommandResult(CLIENT_LOGIN, Action.REDIRECT);
        }
        Client client = service.login(email, password);
        if (!client.getEnabled()){
            logger.info(String.format(BANNED_LOG, client.getId()));
            request.setAttribute("error", ERROR_MESSAGE);
            return new CommandResult(ERROR, Action.FORWARD);
        }
        HttpSession session = request.getSession();
        session.setAttribute("client_id", client.getId());
        session.setAttribute("client_name", client.getName());
        session.setAttribute("userType", UserType.CLIENT);
        logger.info(String.format(LOGGED_IN_LOG, client.getId()));
        return new CommandResult(CLIENT_HOME, Action.REDIRECT);
    }
}
