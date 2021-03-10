package service.admin.Impl;

import dao.adminDao.UserDao_admin;
import entity.User;
import org.springframework.stereotype.Service;
import service.admin.UserService_admin;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserImpl implements UserService_admin {
    @Resource
    private UserDao_admin UserDao_admin;

    @Override
    public List<User> getAllUsers() {
        return UserDao_admin.getAllUsers();
    }

    @Override
    public int blockadeUser(String id) {
        return UserDao_admin.blockadeUser(id);
    }

    @Override
    public int leaseUser(String id) {
        return UserDao_admin.leaseUser(id);
    }
}
