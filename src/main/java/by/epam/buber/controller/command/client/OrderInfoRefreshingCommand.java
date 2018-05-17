package by.epam.buber.controller.command.client;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.model.Driver;
import by.epam.buber.model.RideOrder;
import by.epam.buber.service.DriverService;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderInfoRefreshingCommand implements Command {
    private static final String RIDE = "/WEB-INF/views/client/ride.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {

        HttpSession session = request.getSession();
        RideOrder order = (RideOrder) session.getAttribute("order");
        DriverService service = new DriverService();
        Driver driver = null;
        Integer driverId = order.getDriverId();
        if (driverId != null) {
            driver = service.find(driverId);
        }
        request.setAttribute("driver", driver);
        return new CommandResult(RIDE, Action.FORWARD);
    }
}
