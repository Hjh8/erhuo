package service.admin;

import entity.User;
import java.util.List;

public interface UserService_admin {
    List<User> getAllUsers();
    int blockadeUser(String id);
    int leaseUser(String id);
}
