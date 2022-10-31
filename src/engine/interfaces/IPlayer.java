package engine.interfaces;

import java.util.Collection;

public interface IPlayer {
    int getCoins();

    int removeCoins(int coins);

    void addCoins(int coins);

    Collection<ICard> getCards();

    ICard removeCard();

    void addCard();
}
