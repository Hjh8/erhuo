package Controller.user;

import Result.Result;
import entity.Card;
import entity.CardList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.user.CardService;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/card")
public class CardController {
    @Resource
    private CardService cardService;

    /**
     * 增加商品到购物
     * @param c 用户id，商品id，商品数量等信息
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addCard(@RequestBody Card c){
        Result result = new Result();
        int num = cardService.addCard(c);
        result.setData(num);
        result.setMessage("二货添加成功!");
        result.setCode(200);
        return result;
    }

    /**
     * 查询购物车
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public Result showCard(String userId){
        Result result = new Result();
        List<CardList> cardLists = cardService.showCard(userId);
        result.setData(cardLists);
        result.setMessage("购物车列表查询成功!");
        result.setCode(200);
        return result;
    }

    /**
     * 删除购物车商品
     * @param id 购物车id
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteCard(String id){
        Result result = new Result();
        int num = cardService.removeCard(id);
        result.setData(num);
        result.setMessage("购物车商品删除成功!");
        result.setCode(200);
        return result;
    }
}
