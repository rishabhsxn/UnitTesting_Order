package in.vold.order.bo;

import in.vold.order.bo.exception.BOException;
import in.vold.order.dao.OrderDAO;
import in.vold.order.dto.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
/* Required for initializing objects marked with @Mock */
class OrderBOImplTest {

    @Mock
    OrderDAO orderDAO;
    /* Alternate of OrderDAO orderDAO = mock(OrderDAO.class) */

    @InjectMocks
    OrderBOImpl orderBO;
    /* Alternate of OrderBOImpl orderBO = new OrderBOImpl(orderDAO)
    * This looks for orderDAO (dependencies) marked with @Mock. */

    /* Alternate of @ExtendWith()
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    */

    @Test
    public void placeOrder_shouldCreateOrder() throws SQLException, BOException {
        Order order = new Order();
        when(orderDAO.create(order)).thenReturn(1);

        assertTrue(orderBO.placeOrder(order));
        verify(orderDAO).create(order);
    }

    @Test
    public void placeOrder_shouldNotCreateOrder() throws SQLException, BOException {
        Order order = new Order();
        when(orderDAO.create(order)).thenReturn(0);

        assertFalse(orderBO.placeOrder(order));
        verify(orderDAO).create(order);
    }

    @Test
    public void placeOrder_shouldThrowBOException() throws SQLException{
        Order order = new Order();
        when(orderDAO.create(order)).thenThrow(SQLException.class);

        assertThrows(BOException.class, () -> {
            orderBO.placeOrder(order);
        });
    }

}