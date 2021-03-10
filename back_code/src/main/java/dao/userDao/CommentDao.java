package dao.userDao;

import entity.Comment;

import java.util.List;

public interface CommentDao {
    int addComment(Comment comment);
    List<Comment> queryCommentList(String goodsId);
}
