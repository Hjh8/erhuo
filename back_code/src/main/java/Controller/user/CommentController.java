package Controller.user;

import Result.Result;
import Utils.myDate;
import entity.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.user.CommentService;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    /**
     * 商品评论
     * @param comment 评论内容，商品id，用户id等信息
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addComment(@RequestBody Comment comment){
        Result result = new Result();
        comment.setTime(myDate.getDate());
        int nums = commentService.addComment(comment);
        result.setData(nums);
        result.setMessage("二货评价成功!");
        result.setCode(200);
        return result;
    }

    /**
     * 获取评论列表
     * @param goodsId 商品id
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result queryCommentList(String goodsId){
        Result result = new Result();
        List<Comment> comments = commentService.queryCommentList(goodsId);
        result.setData(comments);
        result.setMessage("获取商品评价成功!");
        result.setCode(200);
        return result;
    }
}
