package service.user;

import entity.Order;
import entity.OrderList;
import entity.OrderShow;
import entity.User;
import java.util.List;

public interface OrderService {
    User queryUser(String buyerId);
    List<OrderShow> getOrderShow(List<String>  id);
    int addOrder(Order[] orders);
    List<OrderList> queryOrder(String buyerId);
    int confirmOrder( String orderId, String goodsId);
    int cancelOrder( String orderId, String goodsId, int nums);
}
