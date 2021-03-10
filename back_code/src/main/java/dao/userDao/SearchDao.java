package dao.userDao;

import entity.Search;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SearchDao {
    List<Search> searchGoods(@Param("value")String value, @Param("page") Integer page);
    List<Search> searchUsers(@Param("value")String value, @Param("page") Integer page);
    int goodsTotalNum(String sort);
    int userTotalNum(String sort);
}
