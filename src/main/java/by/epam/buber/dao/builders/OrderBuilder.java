package by.epam.buber.dao.builders;

import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilder implements EntityBuilder {

    @Override
    public RideOrder build(ResultSet resultSet) throws SQLException {

        Integer id = resultSet.getInt(1);
        Integer clientId = resultSet.getInt(2);
        String street = resultSet.getString(3);
        String destinationStreet = resultSet.getString(4);
        BigDecimal cost = resultSet.getBigDecimal(5);
        String stringRideStatus = resultSet.getString(6);

        Integer driverId = resultSet.getInt(8);

        RideOrder order = new RideOrder();

        order.setId(id);
        order.setStreet(street);
        order.setDestinationStreet(destinationStreet);
        order.setClientId(clientId);
        order.setCost(cost);
        OrderStatus orderStatus = OrderStatus.valueOf(stringRideStatus);
        order.setOrderStatus(orderStatus);
        if (driverId != 0) {
            order.setDriverId(driverId);
        }

        return order;
    }
}
