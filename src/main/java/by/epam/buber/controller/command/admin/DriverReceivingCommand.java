package by.epam.buber.controller.command.admin;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;
import by.epam.buber.controller.command.Command;
import by.epam.buber.model.Driver;
import by.epam.buber.service.DriverService;
import by.epam.buber.service.Impl.DriverServiceImpl;
import by.epam.buber.util.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverReceivingCommand implements Command {
    private static final String DRIVER = "/WEB-INF/views/admin/driver.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        String stringDriverId = request.getParameter("driver_id");
        Integer driverId = Integer.parseInt(stringDriverId);
        DriverService service = new DriverServiceImpl();
        Driver driver = service.getById(driverId);
        request.setAttribute("driver", driver);
        return new CommandResult(DRIVER, Action.FORWARD);
    }
}
