package by.epam.buber.controller.command.client;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.builders.ClientBuilder;
import by.epam.buber.controller.builders.EntityBuilder;
import by.epam.buber.controller.validators.SignUpValidator;
import by.epam.buber.controller.validators.Validator;
import by.epam.buber.model.Client;
import by.epam.buber.service.ClientService;
import by.epam.buber.service.Impl.ClientServiceImpl;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class ClientRegistrationCommand implements Command {
    private static final String SIGN_UP = "/sign-up";
    private static final String CLIENT_HOME = "/client/client-home";
    private static final String ERROR = "/error";
    private static final String DUPLICATE_EMAIL_MESSAGE = "duplicate_email_error";
    private static final String REGISTERED_LOG = "Client %d successfully registered and logged in";

    private static final Logger logger = LoggerFactory.getLogger(ClientRegistrationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        EntityBuilder entityBuilder = new ClientBuilder();
        Client client = (Client) entityBuilder.build(request);

        List<String> fields = Arrays.asList(
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getPassword(),
                client.getPhone());
        Validator validator = new SignUpValidator();
        if (!validator.isValid(fields, request)) {
            logger.warn("Client registration form is not valid");
            return new CommandResult(SIGN_UP, Action.REDIRECT);
        }

        ClientService service = new ClientServiceImpl();
        HttpSession session = request.getSession();
        try {
            service.checkPresence(client.getEmail());
        } catch (ServiceException exception) {
            logger.warn(exception.getMessage(), exception);
            session.setAttribute("error", DUPLICATE_EMAIL_MESSAGE);
            return new CommandResult(ERROR, Action.REDIRECT);
        }
        client = service.save(client);
        session.setAttribute("client_id", client.getId());
        logger.info(String.format(REGISTERED_LOG, client.getId()));
        return new CommandResult(CLIENT_HOME, Action.REDIRECT);
    }
}
