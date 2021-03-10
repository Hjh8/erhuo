package service.user.Impl;

import dao.userDao.CardDao;
import entity.Card;
import entity.CardList;
import org.springframework.stereotype.Service;
import service.user.CardService;
import javax.annotation.Resource;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Resource
    private CardDao cardDao;

    @Override
    public int addCard(Card card) {
        return cardDao.addCard(card);
    }

    @Override
    public List<CardList> showCard(String userId) {
        return cardDao.showCard(userId);
    }

    @Override
    public int removeCard(String id) {
        return cardDao.removeCard(id);
    }
}
