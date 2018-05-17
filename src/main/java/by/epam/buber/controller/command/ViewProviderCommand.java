package by.epam.buber.controller.command;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProviderCommand implements Command{

    private static final String HOME = "/WEB-INF/views/home.jsp";
    private static final String ERROR = "/WEB-INF/views/error.jsp";
    private static final String LOGIN = "/WEB-INF/views/login.jsp";
    private static final String SIGN_UP = "/WEB-INF/views/sign_up.jsp";
    private static final String ADMIN_LOGIN = "/WEB-INF/views/admin_login.jsp";
    private static final String CLIENT_LOGIN = "/WEB-INF/views/client_login.jsp";
    private static final String DRIVER_LOGIN = "/WEB-INF/views/driver_login.jsp";
    private static final String ADMIN_HOME = "/WEB-INF/views/admin/administration.jsp";
    private static final String CLIENTS = "/WEB-INF/views/admin/clients.jsp";
    private static final String CAR_REGISTRATION =
            "/WEB-INF/views/admin/driver_registration.jsp";
    private static final String CLIENT_HOME = "/WEB-INF/views/client/client_home.jsp";
    private static final String RIDE_ORDER = "/WEB-INF/views/client/ride_order.jsp";
    private static final String ACCOUNT_REPLENISHMENT =
            "/WEB-INF/views/client/account_replenishment.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        String view = getView(requestURI);
        return new CommandResult(view, Action.FORWARD);
    }

    private String getView(String requestURI) {
        switch (requestURI) {
            case "/error":
                return ERROR;
            case "/login":
                return LOGIN;
            case "/sign-up":
                return SIGN_UP;
            case "/admin-login":
                return ADMIN_LOGIN;
            case "/client-login":
                return CLIENT_LOGIN;
            case "/driver-login":
                return DRIVER_LOGIN;
            case "/admin/administration":
                return ADMIN_HOME;
            case "/admin/clients":
                return CLIENTS;
            case "/admin/register-car":
                return CAR_REGISTRATION;
            case "/client/client-home":
                return CLIENT_HOME;
            case "/client/ride-order":
                return RIDE_ORDER;
            case "/client/replenish-account":
                return ACCOUNT_REPLENISHMENT;
            default:
                return HOME;
        }
    }
}
