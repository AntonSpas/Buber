package by.epam.buber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler extends HttpServlet {
    private static final String ERROR = "/WEB-INF/views/error.jsp";

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        logger.warn(throwable.getMessage(), throwable);

        request.setAttribute("from_handler", true);
        request.setAttribute("statusCode", statusCode);
        request.setAttribute("exceptionName", throwable.getClass().getName());
        request.setAttribute("message", throwable.getMessage());
        request.setAttribute("requestUri", requestUri);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR);
        requestDispatcher.forward(request, response);
    }
}
