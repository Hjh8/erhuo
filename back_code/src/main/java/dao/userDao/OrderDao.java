package dao.userDao;

import entity.Order;
import entity.OrderList;
import entity.OrderShow;
import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    User queryUser(String buyerId);
    OrderShow getOrderShow(String id);
    int addOrder(Order[] orders);
    int updateGoodsCount(
            @Param("goodsId") String goodsId,
            @Param("nums") int nums);
    List<OrderList> queryOrder(String buyerId);
    int confirmOrder(
            @Param("orderId") String orderId,
            @Param("goodsId") String goodsId);
    int cancelOrder(
            @Param("orderId") String orderId,
            @Param("goodsId") String goodsId,
            @Param("nums") int nums);
}
