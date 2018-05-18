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
import java.util.List;

public class ClientsReceivingCommand implements Command {
    private static final String CLIENTS = "/WEB-INF/views/admin/clients.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        int page = 1;
        int recordsPerPage = 10;
        String pageParameter = request.getParameter("page");
        if(pageParameter != null) {
            page = Integer.parseInt(pageParameter);
        }
        ClientService service = new ClientServiceImpl();
        List<Client> clients = service.getClientsByPage(
                (page-1)*recordsPerPage, recordsPerPage);
        int recordsQuantity = service.getRecordsQuantity();
        int pagesQuantity = (int) Math.ceil(recordsQuantity * 1.0 / recordsPerPage);
        request.setAttribute("clients", clients);
        request.setAttribute("pagesQuantity", pagesQuantity);
        request.setAttribute("currentPage", page);
        return new CommandResult(CLIENTS, Action.FORWARD);
    }
}
