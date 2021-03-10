package service.user;

import entity.GoodsDetail;
import entity.GoodsList;
import java.util.List;

public interface GoodsService {
    List<GoodsList> queryLatestGoods(Integer page);
    List<GoodsList> querySortGoods(String sort, Integer page);
    int addGoods(GoodsDetail goodsDetail);
    int queryTotalNum(String sort);
    GoodsDetail queryGoodsDetail(String goodsId);
}
