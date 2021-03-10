package dao.userDao;

import entity.GoodsDetail;
import entity.GoodsList;
import entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface GoodsDao {

    List<GoodsList> queryLatestGoods(Integer page);
    List<GoodsList> querySortGoods(@Param("sort") String sort, @Param("page") Integer page);
    int addGoods(GoodsDetail goodsDetail);
    int queryTotalNum(String sort);
    GoodsDetail queryGoodsDetail(String goodsId);
    User queryUSer(String id);
}
