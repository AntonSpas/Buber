package by.epam.buber.controller.builders;

import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.CarType;
import by.epam.buber.model.enums.OrderStatus;
import by.epam.buber.service.Impl.OrderServiceImpl;
import by.epam.buber.util.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class RideBuilder implements EntityBuilder {

    @Override
    public RideOrder build(HttpServletRequest request) throws ServiceException {
        String stringCarType = request.getParameter("car_type");
        CarType carType = CarType.valueOf(stringCarType.toUpperCase());
        String destinationStreet = request.getParameter("dest_street");
        HttpSession session = request.getSession(false);
        Integer clientId = (Integer) session.getAttribute("client_id");

        OrderServiceImpl service = new OrderServiceImpl();

        int streetNumber = 1 + (int)(Math.random() * 12);
        String departureStreet = service.getStreetById(streetNumber);
        while(destinationStreet.equals(departureStreet)) {
            streetNumber = 1 + (int)(Math.random() * 12);
            departureStreet = service.getStreetById(streetNumber);
        }

        Double distance = service.getDistance(departureStreet, destinationStreet);
        ResourceBundle resource = ResourceBundle.getBundle("configuration");
        String tariffValue;
        if (carType == CarType.ECONOMY) {
            tariffValue = resource.getString("tariff.economy");
        } else if (carType == CarType.PREMIUM) {
            tariffValue = resource.getString("tariff.premium");
        } else {
            tariffValue = resource.getString("tariff.large");
        }
        Double tariff = Double.parseDouble(tariffValue);
        Double doubleCost = distance * tariff;
        BigDecimal cost = new BigDecimal(doubleCost);
        cost = cost.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        RideOrder rideOrder = new RideOrder();
        rideOrder.setStreet(departureStreet);
        rideOrder.setDestinationStreet(destinationStreet);
        rideOrder.setClientId(clientId);
        rideOrder.setCost(cost);
        rideOrder.setOrderStatus(OrderStatus.RECEIVED);
        rideOrder.setCarType(carType);

        return rideOrder;
    }
}