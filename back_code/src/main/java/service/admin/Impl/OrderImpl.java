package service.admin.Impl;

import dao.adminDao.OrderDao_admin;
import entity.OrderList;
import org.springframework.stereotype.Service;
import service.admin.OrderService_admin;
import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderImpl implements OrderService_admin {
    @Resource
    private OrderDao_admin OrderDao_admin;

    @Override
    public List<OrderList> queryAllOrders() {
        return OrderDao_admin.queryAllOrders();
    }
}
