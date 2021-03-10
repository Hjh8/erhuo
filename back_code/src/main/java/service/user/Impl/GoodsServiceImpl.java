package service.user.Impl;

import dao.userDao.GoodsDao;
import entity.GoodsDetail;
import entity.GoodsList;
import entity.User;
import org.springframework.stereotype.Service;
import service.user.GoodsService;
import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public List<GoodsList> queryLatestGoods(Integer page) {
        return goodsDao.queryLatestGoods((page-1)*8);
    }

    @Override
    public List<GoodsList> querySortGoods(String sort, Integer page) {
        return goodsDao.querySortGoods(sort, (page-1)*8);
    }

    @Override
    public int addGoods(GoodsDetail goodsDetail) {
        return goodsDao.addGoods(goodsDetail);
    }

    @Override
    public int queryTotalNum(String sort) {
        return goodsDao.queryTotalNum(sort);
    }

    @Override
    public GoodsDetail queryGoodsDetail(String goodsId) {
        GoodsDetail goodsDetail = goodsDao.queryGoodsDetail(goodsId);
        String userId = goodsDetail.getSellerId();
        User user = goodsDao.queryUSer(userId);
        goodsDetail.setUser(user);
        return goodsDetail;
    }


}
