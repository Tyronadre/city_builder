package engine.interfaces;

public interface ICardStack {


    /**
     * @param drawStackSizeHint If {@code TRUE} there will be a hint how many cards are in the stack, if there are more cards than set with {@link ICardStack#setStackMaxDrawSize(int)}
     */
    void setDrawStackSizeHint(boolean drawStackSizeHint);

    /**
     * @param drawStackSizeMax the maximal amount of cards that will be painted.
     */
    void setStackMaxDrawSize(int drawStackSizeMax);

    /**
     * Test if a card can be added to the stack.
     *
     * @param card The card to test
     * @return {@code TRUE} if the card can be added, {@code FALSE} otherwise.
     */
    boolean testCard(ICard card);

    /**
     * Adds one card to the stack. This card will be added at the specified position.
     * The cards position and size will be set to this stack default position and size.
     * If the stack is full or the card is not allowed on this stack it will not be added.
     * This method uses the {@link ICardStack#testCard(ICard)} method to test if the card can be added
     *
     * @param card The card to be added
     * @param pos  The position of the card in the stack
     * @return {@code TRUE} if the card was added, {@code FALSE} otherwise
     */
    boolean addCard(ICard card, int pos);

    /**
     * Removes the card at the specified position of the stack, or null if the position is not in the stack.
     *
     * @param card the card to be removed
     * @return the card
     */
    ICard removeCard(ICard card);

    /**
     * Shuffles this stack. If the stack is empty nothing happens
     */
    void shuffel();

    /**
     * Lets the top card be draggable. There may only be one card in the whole game dragged at any moment.
     * If there is another card beeing dragged, or this stack is empty nothing will happen.
     * If the card is released it will either be added on the stack that is over at that moment, or it will be added to this stack again.
     * @param b if the top card should be draggable
     */
    void setTopCardDraggable(boolean b);



}
