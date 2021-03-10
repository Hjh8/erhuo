package service.user.Impl;
import dao.userDao.CommentDao;
import entity.Comment;
import org.springframework.stereotype.Service;
import service.user.CommentService;
import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public List<Comment> queryCommentList(String goodsId) {
        return commentDao.queryCommentList(goodsId);
    }
}
