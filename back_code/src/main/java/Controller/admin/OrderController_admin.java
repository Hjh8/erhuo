package Controller.admin;

import Result.Result;
import entity.OrderList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.admin.OrderService_admin;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin/order")
public class OrderController_admin {
    @Resource
    private OrderService_admin orderService;

    @RequestMapping(value = "/queryAllOrders", method = RequestMethod.GET)
    @ResponseBody
    public Result queryOrder(){
        Result result = new Result();
        List<OrderList> orderLists = orderService.queryAllOrders();
        result.setData(orderLists);
        result.setMessage("查询所有订单成功!");
        result.setCode(200);
        return result;
    }
}
