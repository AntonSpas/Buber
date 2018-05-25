package by.epam.buber.controller.command.driver;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.model.RideOrder;
import by.epam.buber.service.Impl.OrderServiceImpl;
import by.epam.buber.service.OrderService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientAbsenceCommand implements Command {
    private static final String AVAILABLE_ORDERS = "/driver/available-orders";
    private static final String ABSENCE_LOG = "Driver %d noted the client %d absence. Order %d";

    private static final Logger logger = LoggerFactory.getLogger(OrderAcceptanceCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        RideOrder order = (RideOrder) session.getAttribute("order");
        OrderService service = new OrderServiceImpl();
        if (order == null) {
            String stringOrderId = request.getParameter("id");
            Integer orderId = Integer.parseInt(stringOrderId);
            order = service.getById(orderId);
        }
        Integer orderId = order.getId();
        Integer clientId = order.getClientId();
        service.registerAbsence(orderId, clientId);
        if (orderId != null) {
            List<RideOrder> orders = service.getUnconfirmedOrders(order.getDriverId());
            if (!orders.isEmpty()) {
                session.setAttribute("unconfirmed_orders", orders);
            } else {
                session.setAttribute("unconfirmed_present", false);
            }
        }
        logger.info(String.format(ABSENCE_LOG, order.getDriverId(), clientId, orderId));
        return new CommandResult(AVAILABLE_ORDERS, Action.REDIRECT);
    }
}
