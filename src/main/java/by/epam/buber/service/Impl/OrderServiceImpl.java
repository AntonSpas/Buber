package by.epam.buber.service.Impl;

import by.epam.buber.dao.pool.ConnectionPool;
import by.epam.buber.dao.pool.ConnectionWrapper;
import by.epam.buber.dao.ClientDAO;
import by.epam.buber.dao.DAO;
import by.epam.buber.dao.DriverDAO;
import by.epam.buber.dao.OrderDAO;
import by.epam.buber.model.Client;
import by.epam.buber.model.Entity;
import by.epam.buber.model.RideOrder;
import by.epam.buber.model.enums.CarType;
import by.epam.buber.model.enums.OrderStatus;
import by.epam.buber.service.OrderService;
import by.epam.buber.util.DAOException;
import by.epam.buber.util.ServiceException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public RideOrder save(RideOrder rideOrder) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            DAO<RideOrder> dao = new OrderDAO(connection);
            return dao.save(rideOrder);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<RideOrder> getOrdersByPage(int firstRow, int rowCount)
            throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            OrderDAO dao = new OrderDAO(connection);
            return dao.findByPage(firstRow, rowCount);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Integer getRecordsQuantity() throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            OrderDAO dao = new OrderDAO(connection);
            return dao.findMaxId();
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<RideOrder> getAvailableOrders(CarType carType) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            OrderDAO dao = new OrderDAO(connection);
            return dao.findAvailable(carType);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public String getStreetById(int id) throws ServiceException{
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();
            OrderDAO dao = new OrderDAO(connection);
            return dao.findStreetById(id);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void registerAbsence(Integer id, Integer clientId) throws ServiceException{
        Connection connection = null;
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            connection = connectionWrapper.getConnection();

            connection.setAutoCommit(false);

            OrderDAO dao = new OrderDAO(connection);
            dao.changeStatus(OrderStatus.CANCELED, id);
            ClientDAO clientDAO = new ClientDAO(connection);
            clientDAO.addBanScore(clientId);

            connection.commit();

        } catch (DAOException | SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                // log.warn
            }
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public Double getDistance(String startStreet, String destinationStreet)
            throws ServiceException {
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();

            OrderDAO orderDAO = new OrderDAO(connection);
            Double distance = orderDAO.findDistance2(startStreet, destinationStreet);
            return distance;

        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public List<Entity> acceptOrder(Integer orderId, Integer driverId)
            throws ServiceException {
        try (ConnectionWrapper connectionWrapper = new ConnectionWrapper(
                connectionPool.takeConnection())) {
            Connection connection = connectionWrapper.getConnection();

            OrderDAO orderDAO = new OrderDAO(connection);
            orderDAO.addDriver(orderId, driverId);

            DAO<RideOrder> dao = new OrderDAO(connection);
            RideOrder order = dao.findById(orderId);

            Integer clientId = order.getClientId();
            DAO<Client> clientDAO = new ClientDAO(connection);
            Client client = clientDAO.findById(clientId);

            return Arrays.asList(order, client);

        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void executeTransfer(RideOrder order) throws ServiceException {
        ConnectionWrapper connectionWrapper = null;
        try {
            connectionWrapper = new ConnectionWrapper(connectionPool.takeConnection());
            Connection connection = connectionWrapper.getConnection();

            connection.setAutoCommit(false);

            OrderDAO orderDAO = new OrderDAO(connection);
            Integer orderId = order.getId();
            orderDAO.changeStatus(OrderStatus.DONE, orderId);
            BigDecimal cost = order.getCost();
            Integer clientId = order.getClientId();
            ClientDAO clientDAO = new ClientDAO(connection);
            clientDAO.takeMoney(clientId, cost);
            Integer driverId = order.getDriverId();
            DriverDAO driverDAO = new DriverDAO(connection);
            driverDAO.putMoney(driverId, cost);

            connection.commit();

        } catch (DAOException | SQLException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        } finally {
            connectionWrapper.close();
            connectionWrapper.rollback();
        }
    }
}
