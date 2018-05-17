package by.epam.buber.controller.command.client;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.service.ClientService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class AccountReplenishmentCommand implements Command {
    private static final String CLIENT_HOME = "/client/client-home";
//    private static final String ACCOUNT_REPLENISHMENT = "/client/account-replenishment";

    private static final Logger logger = LoggerFactory.getLogger(ClientLoginCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        Integer clientId = (Integer) session.getAttribute("client_id");
        String stringAmount = request.getParameter("amount");
        BigDecimal amount = new BigDecimal(stringAmount);
        ClientService service = new ClientService();
        service.replenishAccount(clientId, amount);
        return new CommandResult(CLIENT_HOME, Action.REDIRECT);
    }
}
