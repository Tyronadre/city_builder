package engine.interfaces;

import eea.engine.event.Event;
import engine.cards.CardClass;
import engine.cards.Type;

public interface ICard {

    /**
     *
     * @return Cost of the card
     */
    int getCost();

    /**
     *
     * @return the type of the card, specified by {@link Type}
     */
    Type getType();

    /**
     *
     * @return the class of the card, specified by {@link Class}
     */
    CardClass getCardClass();

    /**
     * @param paintFront if the front or the back of the card should be painted
     */
    void setPaintFront(boolean paintFront);

    /**
     * returns the Event this card will trigger.
     * @param gameBoard the gameBoard
     * @param player the player who rolled the dice
     */
    Event getEvent(IGameBoard gameBoard, IPlayer player);



}
