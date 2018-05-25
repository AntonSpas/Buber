package by.epam.buber.dao;

import by.epam.buber.dao.builders.EntityBuilder;
import by.epam.buber.dao.builders.OrderBuilder;
import by.epam.buber.model.Entity;
import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.CarType;
import by.epam.buber.model.enums.OrderStatus;
import by.epam.buber.util.DAOException;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class OrderDAO extends AbstractDAO {
    private final static String TABLE = "orders";
    private final static String SQL_INSERT =
            "INSERT INTO orders(street, dest_street, client_id, cost, status, car_type)" +
                    " VALUES(?,?,?,?,?,?)";
    private final static String SQL_UPDATE =
            "UPDATE orders SET street=?, dest_street=?, client_id=?, cost=? WHERE id=?";
    private final static String SQL_ADD_DRIVER =
            "UPDATE orders SET driver_id=?, status=? WHERE id=?";
    private final static String SQL_GET_AVAILABLE =
            "SELECT * FROM orders WHERE status='RECEIVED' AND car_type=?";
    private final static String SQL_CHANGE_STATUS =
            "UPDATE orders SET status=? WHERE id=?";
    private final static String SQL_FIND_STREET =
            "SELECT street FROM streets WHERE id=?";
    private final static String SQL_FIND_DISTANCE = "SELECT distance FROM distance " +
            "WHERE (first_district=(SELECT district FROM streets WHERE street=?)" +
            " AND second_district=(SELECT district FROM streets WHERE street=?)) " +
            "OR (second_district=(SELECT district FROM streets WHERE street=?)" +
            " AND first_district=(SELECT district FROM streets WHERE street=?))";
    private final static String SQL_FIND_BY_PAGE =
            "SELECT * FROM orders ORDER BY id LIMIT ? OFFSET ?";
    private final static String SQL_MAX_ID = "SELECT MAX(id) FROM orders";
    private final static String SQL_FIND_ACTIVE =
            "SELECT * FROM orders WHERE client_id=? AND (status='RECEIVED' OR status='ACCEPTED')";
    private final static String SQL_FIND_ACCEPTED =
            "SELECT * FROM orders WHERE status='ACCEPTED' AND driver_id=?";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    /**
     * Finds orders by page from the database
     *
     * @param firstRow first row identifier
     * @param rowCount number of rows
     * @return list of orders
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public List<RideOrder> findByPage(int firstRow, int rowCount) throws DAOException {
        return executeQuery(SQL_FIND_BY_PAGE, rowCount, firstRow);
    }

    /**
     * Finds last row identifier from orders table
     *
     * @return founded order identifier
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public Integer findMaxId() throws DAOException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_MAX_ID)) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    /**
     * Finds street by identifier
     *
     * @param id street identifier
     * @return founded street
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public String findStreetById(Integer id) throws DAOException {
        try (PreparedStatement statement = prepareStatement(SQL_FIND_STREET, id);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    /**
     * Finds distance between two streets
     *
     * @param firstStreet first street name
     * @param secondStreet second street name
     * @return founded distance
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public Double findDistance(String firstStreet, String secondStreet)
            throws DAOException {
        try (PreparedStatement statement = prepareStatement(SQL_FIND_DISTANCE,
                firstStreet, secondStreet, firstStreet, secondStreet);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getDouble(1);
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    /**
     * Changes order status
     *
     * @param id order identifier
     * @param status new order status
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public void changeStatus(OrderStatus status, Integer id) throws DAOException {
        executeUpdate(SQL_CHANGE_STATUS, status.toString(), id);
    }

    /**
     * Finds all orders related to target car type
     *
     * @param carType target car type
     * @return list of orders
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public List<RideOrder> findAvailable(CarType carType) throws DAOException {
        return executeQuery(SQL_GET_AVAILABLE, carType.toString());
    }

    /**
     * Sets driver to order
     *
     * @param orderId order identifier
     * @param driverId driver identifier
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public void addDriver(Integer orderId, Integer driverId) throws DAOException {
        executeUpdate(SQL_ADD_DRIVER, driverId,
                OrderStatus.ACCEPTED.toString(), orderId);
    }

    /**
     * Finds active client order
     *
     * @param clientId client identifier
     * @return  active order or null if there is none
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public RideOrder findActiveOrder(Integer clientId) throws DAOException {
        List<RideOrder> orders = executeQuery(SQL_FIND_ACTIVE, clientId);
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
    }

    /**
     * Finds all driver unconfirmed orders
     *
     * @param driverId driver identifier
     * @return list of orders
     * @throws DAOException if any exceptions occurs in the dao layer
     */
    public List<RideOrder> findUnconfirmedOrders(Integer driverId) throws DAOException {
        return executeQuery(SQL_FIND_ACCEPTED, driverId);
    }

    @Override
    public String getTableName() {
        return TABLE;
    }

    @Override
    public String getCreateQuery() {
        return SQL_INSERT;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE;
    }

    @Override
    public EntityBuilder getEntityBuilder() {
        return new OrderBuilder();
    }

    @Override
    public List<Object> getFields(Entity entity) {
        RideOrder rideOrder = (RideOrder) entity;
        return Arrays.asList(
                rideOrder.getStreet(),
                rideOrder.getDestinationStreet(),
                rideOrder.getClientId(),
                rideOrder.getCost(),
                rideOrder.getOrderStatus().toString(),
                rideOrder.getCarType().toString());
    }
}
