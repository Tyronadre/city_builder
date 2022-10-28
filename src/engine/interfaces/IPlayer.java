package engine.interfaces;

import java.util.Collection;

public interface IPlayer {
    int getCoins();

    int removeCoins();

    int addCoins();

    Collection<ICard> getCards();

    ICard removeCard();

    void addCard();
}
