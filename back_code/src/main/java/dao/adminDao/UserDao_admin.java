package dao.adminDao;

import entity.User;
import java.util.List;

public interface UserDao_admin {
    List<User> getAllUsers();
    int blockadeUser(String id);
    int leaseUser(String id);
}
