package by.epam.buber.controller.command.client;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.validators.ReplenishmentValidator;
import by.epam.buber.service.ClientService;
import by.epam.buber.service.Impl.ClientServiceImpl;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class AccountReplenishmentCommand implements Command {
    private static final String CLIENT_HOME = "/client/client-home";
    private static final String ACCOUNT_REPLENISHMENT =
            "/client/replenish-account";
    private static final String REPLENISHED_LOG = "Client %d put %s $ to the account";
    private static final String VALIDATION_LOG = "Account replenishment form is not valid";

    private static final Logger logger = LoggerFactory.getLogger(ClientLoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        Integer clientId = (Integer) session.getAttribute("client_id");
        String AmountValue = request.getParameter("amount");
        List<String> fields = Collections.singletonList(AmountValue);
        ReplenishmentValidator validator = new ReplenishmentValidator();
        if (!validator.isValid(fields, request)) {
            logger.warn(VALIDATION_LOG);
            return new CommandResult(ACCOUNT_REPLENISHMENT, Action.REDIRECT);
        }
        BigDecimal amount = new BigDecimal(AmountValue);
        ClientService service = new ClientServiceImpl();
        service.replenishAccount(clientId, amount);
        logger.info(String.format(REPLENISHED_LOG, clientId, amount));
        return new CommandResult(CLIENT_HOME, Action.REDIRECT);
    }
}
