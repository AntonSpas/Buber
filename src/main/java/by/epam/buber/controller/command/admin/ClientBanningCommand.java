package by.epam.buber.controller.command.admin;

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

public class ClientBanningCommand implements Command {
    private static final String CLIENTS = "/admin/clients";
    private static final String CLIENT_BANNING_LOG = "Client %d was banned by admin %d";

    private static final Logger logger = LoggerFactory.getLogger(ClientBanningCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String stringClientId = request.getParameter("client_id");
        Integer clientId = Integer.parseInt(stringClientId);
        ClientService service = new ClientService();
        service.banClient(clientId);
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("admin_id");
        logger.info(String.format(CLIENT_BANNING_LOG, clientId, adminId));
        return new CommandResult(CLIENTS, Action.REDIRECT);
    }
}
