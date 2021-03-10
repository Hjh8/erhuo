package service.user;

import entity.Card;
import entity.CardList;

import java.util.List;

public interface CardService {
    int addCard(Card card);
    List<CardList> showCard(String userId);
    int removeCard(String id);
}
