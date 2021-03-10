package service.user.Impl;

import dao.userDao.UserDao;
import entity.GoodsDetail;
import entity.User;
import org.springframework.stereotype.Service;
import service.user.UserService;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int registerUser(User user) {
        return userDao.registerUser(user);
    }

    @Override
    public User loginUser(String phone, String pwd) {
        // 密码错误或无此用户
        if (userDao.getNum(phone, pwd) == 0) return null;
        User user = userDao.loginUser(phone, pwd);
        return user;
    }

    @Override
    public int retrievePwd(String phone, String newPwd) {
        return userDao.retrievePwd(phone, newPwd);
    }

    @Override
    public List<GoodsDetail> queryRelease(String userId) {
        return userDao.queryRelease(userId);
    }

    @Override
    public int removeRelease(String goodsId) {
        return userDao.removeRelease(goodsId);
    }
}
