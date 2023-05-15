package in.vold.order.bo;

import in.vold.order.bo.exception.BOException;
import in.vold.order.dto.Order;

public interface OrderBO {
    boolean placeOrder(Order order) throws BOException;
    boolean cancelOrder(int id) throws BOException;
    boolean deleteOrder(int id) throws BOException;
}
