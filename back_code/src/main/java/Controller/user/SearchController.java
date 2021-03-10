package Controller.user;

import Result.Result;
import entity.Search;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.user.SearchService;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/search")
public class SearchController {
    @Resource
    private SearchService searchService;

    /**
     * 获取搜索商品的数量
     * @param value 商品名
     * @return 结果集对象
     */
    @RequestMapping(value = "/goodsNum", method = RequestMethod.GET)
    @ResponseBody
    public Result queryGoodsNums(String value) {
        Result result = new Result();
        int nums= searchService.goodsTotalNum(value);
        result.setData(nums);
        result.setMessage("查找商品成功");
        result.setCode(200);
        return result;
    }

    /**
     * 查询商品
     * @param value 模糊查询的 商品名 或 id
     * @param page 页数
     * @return 结果集对象
     */
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    @ResponseBody
    public Result searchGoods(String value, Integer page) {
        Result result = new Result();
        List<Search> searchList= searchService.searchGoods(value, page);
        result.setData(searchList);
        result.setMessage("查找商品成功");
        result.setCode(200);
        return result;
    }

    /**
     * 获取搜索用户的数量
     * @param value 用户名
     * @return 结果集对象
     */
    @RequestMapping(value = "/userNum", method = RequestMethod.GET)
    @ResponseBody
    public Result queryUserNums(String value) {
        Result result = new Result();
        int nums= searchService.userTotalNum(value);
        result.setData(nums);
        result.setMessage("查找用户品成功");
        result.setCode(200);
        return result;
    }

    /**
     * 查询用户
     * @param value 搜索的值
     * @param page 页码
     * @return 结果集对象
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Result searchUser(String value, Integer page) {
        Result result = new Result();
        List<Search> searchList= searchService.searchUsers(value, page);
        result.setData(searchList);
        result.setMessage("查找发布者成功");
        result.setCode(200);
        return result;
    }

}
