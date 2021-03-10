package dao.userDao;

import entity.Card;
import entity.CardList;

import java.util.List;

public interface CardDao {
    int addCard(Card card);
    List<CardList> showCard(String userId);
    int removeCard(String id);
}
