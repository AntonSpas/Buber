package by.epam.buber.controller.command.driver;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.CarType;
import by.epam.buber.service.Impl.OrderServiceImpl;
import by.epam.buber.service.OrderService;
import by.epam.buber.util.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrdersChoosingCommand implements Command {
    private static final String AVAILABLE_ORDERS =
            "/WEB-INF/views/driver/available_orders.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession();
        CarType carType = (CarType) session.getAttribute("car_type");
        OrderService service = new OrderServiceImpl();
        List<RideOrder> orders = service.getAvailableOrders(carType);
        request.setAttribute("orders", orders);
        return new CommandResult(AVAILABLE_ORDERS, Action.FORWARD);
    }
}
