package in.vold.order.bo;

import in.vold.order.bo.exception.BOException;
import in.vold.order.dao.OrderDAO;
import in.vold.order.dto.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO{

    private OrderDAO orderDAO;


    public OrderBOImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public boolean placeOrder(Order order) throws BOException {
        try {
            int result = orderDAO.create(order);

            if(result == 0)
                return false;
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BOException {
        try {
            Order order = orderDAO.read(id);
            order.setStatus("Cancelled");

            int result = orderDAO.update(order);

            if(result == 0)
                return false;
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BOException {
        try {
            int result = orderDAO.delete(id);

            if(result == 0)
                return false;
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }
}
