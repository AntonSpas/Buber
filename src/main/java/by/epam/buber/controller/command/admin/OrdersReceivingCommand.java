package by.epam.buber.controller.command.admin;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.model.RideOrder;
import by.epam.buber.service.OrderService;
import by.epam.buber.util.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrdersReceivingCommand implements Command {
    private static final String ORDERS = "/WEB-INF/views/admin/orders.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        int page = 1;
        int recordsPerPage = 10;
        String pageParameter = request.getParameter("page");
        if(pageParameter != null) {
            page = Integer.parseInt(pageParameter);
        }
        OrderService service = new OrderService();
        List<RideOrder> orders = service.getOrdersByPage(
                (page-1)*recordsPerPage, recordsPerPage);
        int recordsQuantity = service.getRecordsQuantity();
        int pagesQuantity = (int) Math.ceil(recordsQuantity * 1.0 / recordsPerPage);
        request.setAttribute("orders", orders);
        request.setAttribute("pagesQuantity", pagesQuantity);
        request.setAttribute("currentPage", page);
        return new CommandResult(ORDERS, Action.FORWARD);
    }
}
