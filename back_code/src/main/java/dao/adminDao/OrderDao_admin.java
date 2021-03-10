package dao.adminDao;

import entity.OrderList;
import java.util.List;

public interface OrderDao_admin {
    List<OrderList> queryAllOrders();
}
