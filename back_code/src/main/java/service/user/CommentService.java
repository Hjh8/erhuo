package service.user;

import entity.Comment;

import java.util.List;

public interface CommentService {
    int addComment(Comment comment);
    List<Comment> queryCommentList(String goodsId);
}
