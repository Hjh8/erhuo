package service.user.Impl;

import dao.userDao.SearchDao;
import entity.Search;
import org.springframework.stereotype.Service;
import service.user.SearchService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private SearchDao searchDao;

    @Override
    public List<Search> searchGoods(String value, Integer page) {
        return searchDao.searchGoods("%"+value+"%", (page-1)*8);
    }

    @Override
    public List<Search> searchUsers(String value, Integer page) {
        return searchDao.searchUsers("%"+value+"%", (page-1)*8);
    }

    @Override
    public int goodsTotalNum(String value) {
        return searchDao.goodsTotalNum("%"+value+"%");
    }

    @Override
    public int userTotalNum(String value) {
        return searchDao.userTotalNum("%"+value+"%");
    }
}
