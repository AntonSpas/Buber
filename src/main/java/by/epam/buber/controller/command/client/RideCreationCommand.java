package by.epam.buber.controller.command.client;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.builders.EntityBuilder;
import by.epam.buber.controller.builders.RideBuilder;
import by.epam.buber.model.RideOrder;
import by.epam.buber.service.OrderService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RideCreationCommand implements Command {
    private static final String RIDE = "/client/ride";
    private static final String ORDER_CREATED_LOG = "Ride order %d was successfully created";

    private static final Logger logger = LoggerFactory.getLogger(RideCreationCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        EntityBuilder entityBuilder = new RideBuilder();
        RideOrder order = (RideOrder) entityBuilder.build(request);
        OrderService service = new OrderService();
        order = service.save(order);
        HttpSession session = request.getSession();
        session.setAttribute("order", order);
        logger.info(String.format(ORDER_CREATED_LOG, order.getId()));
        return new CommandResult(RIDE, Action.REDIRECT);
    }
}