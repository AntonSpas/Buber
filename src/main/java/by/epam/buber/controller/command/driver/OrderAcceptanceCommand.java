package by.epam.buber.controller.command.driver;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.model.Client;
import by.epam.buber.model.Entity;
import by.epam.buber.model.RideOrder;
import by.epam.buber.service.OrderService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderAcceptanceCommand implements Command {
    private static final String CHOSEN_ORDER = "/WEB-INF/views/driver/chosen_order.jsp";
    private static final String ORDER_ACCEPTANCE_LOG = "Order %d was chosen by driver %d";

    private static final Logger logger = LoggerFactory.getLogger(OrderAcceptanceCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String stringOrderId = request.getParameter("id");
        Integer orderId = Integer.parseInt(stringOrderId);

        HttpSession session = request.getSession();
        Integer driverId = (Integer) session.getAttribute("driver_id");

        OrderService service = new OrderService();
        List<Entity> entities = service.acceptOrder(orderId, driverId);
        RideOrder order = (RideOrder) entities.get(0);
        Client client = (Client) entities.get(1);
        session.setAttribute("order", order);
        session.setAttribute("client", client);
        logger.info(String.format(ORDER_ACCEPTANCE_LOG, orderId, driverId));
        return new CommandResult(CHOSEN_ORDER, Action.FORWARD);
    }
}
