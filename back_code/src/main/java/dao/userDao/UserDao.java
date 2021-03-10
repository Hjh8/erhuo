package dao.userDao;

import entity.GoodsDetail;
import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int registerUser(User user);
    int getNum(@Param("phone") String phone, @Param("pwd") String pwd);
    User loginUser(@Param("phone") String phone, @Param("pwd") String pwd);
    int retrievePwd(@Param("phone") String phone, @Param("newPwd") String newPwd);
    List<GoodsDetail> queryRelease(String userId);
    int removeRelease(String goodsId);

}
