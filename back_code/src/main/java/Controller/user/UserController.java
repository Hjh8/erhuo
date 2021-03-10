package Controller.user;

import Result.Result;
import Utils.Token;
import Utils.myDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import entity.GoodsDetail;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.user.Impl.UserServiceImpl;
import javax.annotation.Resource;
import Exception.UserException;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    /**
     * 用户注册功能
     * @param user 注册信息
     * @return 结果集对象
     * @throws UserException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestBody User user) throws UserException {
        Result<User> result = new Result<User>();
        user.setCreateTime(myDate.getDate());
        user.setSta(1);

        int nums = userService.registerUser(user);
        if(nums != 1){
            throw new UserException("注册失败！");
        }
        result.setMessage("注册成功");
        result.setCode(200);
        return result;
    }

    /**
     * 用户登录功能
     * @param u 电话号码和密码
     * @return Json的字符串格式
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User u) throws JsonProcessingException {
        Result result = new Result<User>();
        User user = userService.loginUser(u.getPhone(), u.getPwd());

        if(user == null){
            result.setMessage("用户名或密码错误");
            result.setData(null);
            result.setCode(404);
        }
        else if(user.getSta() != 1){
            result.setMessage("用户已被封号！");
            result.setData(null);
            result.setCode(403);
        }
        else {
            // 设置token
            String token = Token.buildToken(user.getId());
            user.setToken(token);

            result.setCode(200);
            result.setData(user);
            result.setMessage("登录成功");
        }

        return result.toJson();
    }

    /**
     * 用户修改密码
     * @param phone
     * @param newPwd
     * @return
     */
    @RequestMapping(value = "/retrievePwd", method = RequestMethod.PUT)
    @ResponseBody
    public Result retrievePwd(String phone, String newPwd) {
        int nums = userService.retrievePwd(phone, newPwd);
        Result result = new Result<User>();
        result.setData(nums);
        result.setCode(200);
        result.setMessage("密码修改成功！");
        return result;
    }

    /**
     * 用户查询商品分布
     * @param userId 用户的id
     * @return
     */
    @RequestMapping(value = "/myRelease")
    @ResponseBody
    public Result queryRelease(String userId) {
        Result result = new Result<User>();
        List<GoodsDetail> goodsDetails = userService.queryRelease(userId);
        result.setData(goodsDetails);
        result.setCode(200);
        result.setMessage("查询二货发布成功！");
        return result;
    }

    /**
     * 用户删除商品发布
     * @param goodsId 商品id
     * @return
     */
    @RequestMapping(value = "/removeRelease", method = RequestMethod.DELETE)
    @ResponseBody
    public Result removeRelease(String goodsId) {
        Result result = new Result<User>();
        int nums = userService.removeRelease(goodsId);
        result.setData(nums);
        result.setCode(200);
        result.setMessage("删除二货成功！");
        return result;
    }
}
