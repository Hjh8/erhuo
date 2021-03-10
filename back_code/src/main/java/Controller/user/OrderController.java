package Controller.user;

import Result.Result;
import Utils.myDate;
import entity.Order;
import entity.OrderList;
import entity.OrderShow;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import service.user.OrderService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Result queryUserMsg(String buyerId){
        Result result = new Result();
        User user = orderService.queryUser(buyerId);
        result.setData(user);
        result.setMessage("查询买家信息成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public Result getOrderShow(@RequestBody String[] ids){
        Result result = new Result();
        List<OrderShow> orderShows = orderService.getOrderShow(Arrays.asList(ids));
        result.setData(orderShows);
        result.setMessage("查询买家信息成功!");
        result.setCode(200);
        return result;
    }

    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addOrder(@RequestBody Order[] orders){
        Result result = new Result();
        for(Order o : orders){
            o.setTime(myDate.getDate());
        }

        int nums = orderService.addOrder(orders);
        result.setData(nums);
        result.setMessage("订单增加成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/queryOrder", method = RequestMethod.GET)
    @ResponseBody
    public Result queryOrder(String buyerId){
        Result result = new Result();

        List<OrderList> orderLists = orderService.queryOrder(buyerId);
        result.setData(orderLists);
        result.setMessage("查询历史订单成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    @ResponseBody
    public Result confirmOrder(String orderId, String goodsId){
        Result result = new Result();
        System.out.println(goodsId);
        int nums = orderService.confirmOrder(orderId, goodsId);
        result.setData(nums);
        result.setMessage("收货成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    @ResponseBody
    public Result cancelOrder(String orderId, String goodsId, int nums){
        Result result = new Result();
        int n = orderService.cancelOrder(orderId, goodsId, nums);
        result.setData(n);
        result.setMessage("取消订单成功!");
        result.setCode(200);
        return result;
    }
}
