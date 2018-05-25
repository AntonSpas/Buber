package by.epam.buber.service;

import by.epam.buber.model.Entity;
import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.CarType;
import by.epam.buber.util.ServiceException;

import java.util.List;

public interface OrderService {

    /**
     * Saves order to underlying data storage
     *
     * @param rideOrder order to save
     * @throws ServiceException if any exceptions occurs
     */
    RideOrder save(RideOrder rideOrder) throws ServiceException;

    /**
     * Returns order by identifier
     *
     * @param id order identifier
     * @return founded order
     * @throws ServiceException if order not found or any other exceptions occurs
     */
    RideOrder getById(Integer id) throws ServiceException;

    /**
     * Returns orders by page
     *
     * @param firstRow first row identifier
     * @param rowCount number of rows
     * @return list of orders
     * @throws ServiceException if any exceptions occurs
     */
    List<RideOrder> getOrdersByPage(int firstRow, int rowCount)
            throws ServiceException;

    /**
     * Returns number of orders
     *
     * @return number of orders
     * @throws ServiceException if any exceptions occurs
     */
    Integer getRecordsQuantity() throws ServiceException;

    /**
     * Returns all orders related to target car type
     *
     * @param carType target car type
     * @return list of orders
     * @throws ServiceException if any exceptions occurs
     */
    List<RideOrder> getAvailableOrders(CarType carType) throws ServiceException;

    /**
     * Returns street by identifier
     *
     * @param id street identifier
     * @return founded street
     * @throws ServiceException if street not found or any other exceptions occurs
     */
    String getStreetById(int id) throws ServiceException;

    /**
     * Registers client absence.
     * Changes order status to CANCELED
     * Adds ban score to client
     *
     * @param id order identifier
     * @param clientId client identifier
     * @throws ServiceException if any exceptions occurs
     */
    void registerAbsence(Integer id, Integer clientId) throws ServiceException;

    /**
     * Returns distance between two streets
     *
     * @param startStreet start street name
     * @param destinationStreet destination street name
     * @return founded distance
     * @throws ServiceException if distance not found or any other exceptions occurs
     */
    Double getDistance(String startStreet, String destinationStreet)
            throws ServiceException;

    /**
     * Returns active client order
     *
     * @param clientId client identifier
     * @return  active order or null if there is none
     * @throws ServiceException if any exceptions occurs
     */
    RideOrder getActiveOrder(Integer clientId) throws ServiceException;

    /**
     * Returns all driver unconfirmed orders
     *
     * @param driverId driver identifier
     * @return list of orders
     * @throws ServiceException if any exceptions occurs
     */
    List<RideOrder> getUnconfirmedOrders(Integer driverId) throws ServiceException;

    /**
     * Returns order with added driver and client related to order
     *
     * @param orderId order identifier
     * @param driverId driver identifier
     * @return list of order and client related to order
     * @throws ServiceException if order or client not found or any other exceptions occurs
     */
    List<Entity> acceptOrder(Integer orderId, Integer driverId)
            throws ServiceException;

    /**
     * Executes money transfer from the client account to driver account.
     * Changes order status to DONE
     *
     * @param order
     * @throws ServiceException if any exceptions occurs
     */
    void executeTransfer(RideOrder order) throws ServiceException;
}
