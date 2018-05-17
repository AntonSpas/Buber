package by.epam.buber.controller;

import by.epam.buber.controller.command.Command;
import by.epam.buber.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet{
    private static final String ERROR = "/WEB-INF/views/error.jsp";
    private static final String ERROR_MESSAGE = "default_error";

    private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Command command;
        CommandResult commandResult;
        RequestDispatcher requestDispatcher;
        try {
            command = CommandFactory.create(request);
            commandResult = command.execute(request, response);
            String view = commandResult.getView();

//            System.out.println("FrontController" + view + " " + commandResult.getAction());

            if (Action.REDIRECT == commandResult.getAction()) {
                response.sendRedirect(view);
            } else {
                requestDispatcher = request.getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException exception) {
            logger.warn(exception.getMessage(), exception);
            request.setAttribute("error", ERROR_MESSAGE);
            requestDispatcher = request.getRequestDispatcher(ERROR);
            requestDispatcher.forward(request, response);
        }
    }
}
