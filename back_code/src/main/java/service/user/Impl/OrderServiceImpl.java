package service.user.Impl;

import dao.userDao.OrderDao;
import entity.Order;
import entity.OrderList;
import entity.OrderShow;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.user.OrderService;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Override
    public User queryUser(String buyerId) {
        return orderDao.queryUser(buyerId);
    }

    @Transactional
    @Override
    public List<OrderShow> getOrderShow(List<String> ids) {
        List<OrderShow> orderShows = new ArrayList<>();
        for (String id : ids) {
            orderShows.add(orderDao.getOrderShow(id));
        }
        return orderShows;
    }

    @Transactional
    @Override
    public int addOrder(Order[] orders) {
        // 增加订单
        int nums = orderDao.addOrder(orders);
        // 更新数量
        for(Order o : orders){
            orderDao.updateGoodsCount(o.getGoodsId(), o.getNums());
        }

        return nums;
    }

    @Override
    public List<OrderList> queryOrder(String buyerId) {
        return orderDao.queryOrder(buyerId);
    }

    @Override
    public int confirmOrder(String orderId, String goodsId) {
        return orderDao.confirmOrder(orderId, goodsId);
    }

    @Override
    public int cancelOrder(String orderId, String goodsId, int nums) {
        return orderDao.cancelOrder(orderId, goodsId, nums);
    }

}
