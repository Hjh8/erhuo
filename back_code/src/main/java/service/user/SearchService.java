package service.user;

import entity.Search;
import java.util.List;

public interface SearchService {
    List<Search> searchGoods(String value, Integer page);
    List<Search> searchUsers(String value, Integer page);
    int goodsTotalNum(String sort);
    int userTotalNum(String sort);
}
