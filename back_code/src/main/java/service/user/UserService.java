package service.user;

import entity.GoodsDetail;
import entity.User;
import java.util.List;

public interface UserService {
    int registerUser(User user);
    User loginUser(String phone, String pwd);
    int retrievePwd(String phone, String newPwd);
    List<GoodsDetail> queryRelease(String userId);
    int removeRelease(String goodsId);
}
