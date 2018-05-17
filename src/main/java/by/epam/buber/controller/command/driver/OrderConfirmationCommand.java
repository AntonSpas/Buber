package by.epam.buber.controller.command.driver;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.model.RideOrder;
import by.epam.buber.service.OrderService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderConfirmationCommand implements Command {
    private static final String AVAILABLE_ORDERS = "/driver/available-orders";
    private static final String CONFIRMATION_LOG = "Order %d was confirmed by driver %d";

    private static final Logger logger = LoggerFactory.getLogger(OrderConfirmationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        RideOrder order = (RideOrder) session.getAttribute("order");
        OrderService service = new OrderService();
        service.executeTransfer(order);
        logger.info(String.format(CONFIRMATION_LOG, order.getId(), order.getDriverId()));
        return new CommandResult(AVAILABLE_ORDERS, Action.REDIRECT);
    }
}
