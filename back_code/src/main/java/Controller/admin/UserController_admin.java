package Controller.admin;

import Result.Result;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.admin.UserService_admin;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin/user")
public class UserController_admin {
    @Resource
    private UserService_admin UserService_admin;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ResponseBody
    public Result getAllUsers(){
        Result result = new Result();
        List<User> users = UserService_admin.getAllUsers();
        result.setData(users);
        result.setMessage("查询用户成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/blockadeUser", method = RequestMethod.GET)
    @ResponseBody
    public Result blockadeUser(String id){
        Result result = new Result();
        int nums = UserService_admin.blockadeUser(id);
        result.setData(nums);
        result.setMessage("封号成功!");
        result.setCode(200);
        return result;
    }

    @RequestMapping(value = "/user/leaseUser", method = RequestMethod.GET)
    @ResponseBody
    public Result leaseUser(String id){
        Result result = new Result();
        int nums = UserService_admin.leaseUser(id);
        result.setData(nums);
        result.setMessage("解封成功!");
        result.setCode(200);
        return result;
    }

}
