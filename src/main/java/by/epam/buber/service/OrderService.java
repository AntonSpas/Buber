package by.epam.buber.service;

import by.epam.buber.model.Entity;
import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.CarType;
import by.epam.buber.util.ServiceException;

import java.util.List;

public interface OrderService {
    RideOrder save(RideOrder rideOrder) throws ServiceException;
    RideOrder findById(Integer id) throws ServiceException;
    List<RideOrder> getOrdersByPage(int firstRow, int rowCount)
            throws ServiceException;
    Integer getRecordsQuantity() throws ServiceException;
    List<RideOrder> getAvailableOrders(CarType carType) throws ServiceException;
    String getStreetById(int id) throws ServiceException;
    void registerAbsence(Integer id, Integer clientId) throws ServiceException;
    Double getDistance(String startStreet, String destinationStreet)
            throws ServiceException;
    RideOrder getActiveOrder(Integer cloentId) throws ServiceException;
    List<RideOrder> getUnconfirmedOrders(Integer driverId) throws ServiceException;
    List<Entity> acceptOrder(Integer orderId, Integer driverId)
            throws ServiceException;
    void executeTransfer(RideOrder order) throws ServiceException;
}
