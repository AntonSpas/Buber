package by.epam.buber.controller;

import by.epam.buber.controller.command.Command;
import by.epam.buber.controller.command.LogoutCommand;
import by.epam.buber.controller.command.ViewProviderCommand;
import by.epam.buber.controller.command.admin.*;
import by.epam.buber.controller.command.client.*;
import by.epam.buber.controller.command.driver.*;
import by.epam.buber.util.CommandException;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String LOGOUT= "/logout";
    private static final String SIGN_UP = "/sign-up";
    private static final String ADMIN_LOGIN = "/admin-login";
    private static final String CLIENT_LOGIN = "/client-login";
    private static final String DRIVER_LOGIN = "/driver-login";
    private static final String CAR_REGISTRATION = "/admin/register-car";
    private static final String ORDERS = "/admin/orders";
    private static final String CLIENTS = "/admin/clients";
    private static final String CLIENT_BANNING = "/admin/ban-client";
    private static final String RIDE_ORDER = "/client/ride-order";
    private static final String ACCOUNT_REPLENISHMENT = "/client/replenish-account";
    private static final String RIDE = "/client/ride";
    private static final String AVAILABLE_ORDERS = "/driver/available-orders";
    private static final String CHOSEN_ORDER = "/driver/chosen-order";
    private static final String CONFIRM_ORDER = "/driver/confirm";
    private static final String CLIENT_ABSENCE = "/driver/absence";

    public static Command create(HttpServletRequest request) throws CommandException {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        switch (method) {
            case GET: {
                switch (requestURI) {
                    case LOGOUT:
                        return new LogoutCommand();
                    case AVAILABLE_ORDERS:
                        return new OrdersChoosingCommand();
                    case RIDE_ORDER:
                        return new AccountCheckingCommand();
                    case RIDE:
                        return new OrderInfoRefreshingCommand();
                    case CHOSEN_ORDER:
                        return new OrderAcceptanceCommand();
                    case CONFIRM_ORDER:
                        return new OrderConfirmationCommand();
                    case CLIENT_ABSENCE:
                        return new ClientAbsenceCommand();
                    case CLIENT_BANNING:
                        return new ClientBanningCommand();
                    case ORDERS:
                        return new OrdersReceivingCommand();
                    case CLIENTS:
                        return new ClientsReceivingCommand();
                    default:
                        return new ViewProviderCommand();
                }
            }
            case POST: {
                switch (requestURI) {
                    case ADMIN_LOGIN:
                        return new AdminLoginCommand();
                    case CLIENT_LOGIN:
                        return new ClientLoginCommand();
                    case DRIVER_LOGIN:
                        return new DriverLoginCommand();
                    case SIGN_UP:
                        return new ClientRegistrationCommand();
                    case RIDE_ORDER:
                        return new RideCreationCommand();
                    case CAR_REGISTRATION:
                        return new DriverRegistrationCommand();
                    case ACCOUNT_REPLENISHMENT:
                        return new AccountReplenishmentCommand();
                    default:
                        throw new CommandException("Unsupported command!");
                }
            }
        }
        return null;
    }
}
