package by.epam.buber.controller.command.client;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.model.Client;
import by.epam.buber.service.ClientService;
import by.epam.buber.service.Impl.ClientServiceImpl;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class AccountCheckingCommand implements Command {
    private static final String RIDE_ORDER = "/WEB-INF/views/client/ride_order.jsp";
    private static final String CLIENT_HOME = "/WEB-INF/views/client/client_home.jsp";
    private static final String NEGATIVE_ACCOUNT_LOG =
            "Client %d tried to get a ride, but he has negative account";

    private static final Logger logger = LoggerFactory.getLogger(RideCreationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("client_id");
        ClientService clientService = new ClientServiceImpl();
        Client client = clientService.getById(clientId);
        if (client.getAccount().compareTo(new BigDecimal(0)) == -1){
            logger.info(String.format(NEGATIVE_ACCOUNT_LOG, clientId));
            request.setAttribute("negative_account", true);
            return new CommandResult(CLIENT_HOME, Action.FORWARD);
        }
        return new CommandResult(RIDE_ORDER, Action.FORWARD);
    }
}
