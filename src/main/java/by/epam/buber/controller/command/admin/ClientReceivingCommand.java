package by.epam.buber.controller.command.admin;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.model.Client;
import by.epam.buber.service.ClientService;
import by.epam.buber.service.Impl.ClientServiceImpl;
import by.epam.buber.util.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientReceivingCommand implements Command {
    private static final String CLIENT = "/WEB-INF/views/admin/client.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String stringClientId = request.getParameter("client_id");
        Integer clientId = Integer.parseInt(stringClientId);
        ClientService service = new ClientServiceImpl();
        Client client = service.getById(clientId);
        request.setAttribute("client", client);
        return new CommandResult(CLIENT, Action.FORWARD);
    }
}
