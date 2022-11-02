package engine.cards;

import eea.engine.component.Component;
import eea.engine.entity.Entity;
import engine.interfaces.ICard;
import engine.interfaces.ICardStack;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

//keine renderpolicy, wir machen das einfach mit den karten.

public class CardStack extends Entity implements ICardStack {
    private static final int X_CARD_OFFSET = 150;
    private static final int Y_CARD_OFFSET = 4;
    boolean drawStackSizeHint;
    int drawStackMaxDrawSize;
    List<Card> cards;
    Predicate<Card> stackrule;

    public CardStack(String entityID) {
        super(entityID);
        stackrule = card -> true;
        cards = new ArrayList<>();
        drawStackMaxDrawSize = 10;
        drawStackSizeHint = false;
    }

    @Override
    public void setDrawStackSizeHint(boolean drawStackSizeHint) {
        this.drawStackSizeHint = drawStackSizeHint;
    }

    @Override
    public void setStackMaxDrawSize(int drawStackMaxDrawSize) {
        this.drawStackMaxDrawSize = drawStackMaxDrawSize;
    }

    @Override
    public boolean testCard(ICard card) {
        return stackrule.test((Card) card);
    }

    @Override
    public void addCard(ICard card, int pos) {
        if (!stackrule.test((Card) card))
            throw new IllegalArgumentException("Card: " + card + " can't be on this Stack: " + this);
        cards.add(pos,(Card) card);
    }

    public void addCard(ICard card) {
        addCard(card,cards.size());
    }

    @Override
    public void removeCard(ICard card) {
        cards.remove((Card)card);
    }

    @Override
    public void shuffel() {
        Collections.shuffle(cards);
    }

    @Override
    public void setTopCardDraggable(boolean b) {

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        int max = Math.min(cards.size(), drawStackMaxDrawSize);
        Vector2f cardPos = new Vector2f(getPosition());
        for (int i = 0; i < max; i++) {
            Card card = cards.get(cards.size() - max + i);
            card.setPosition(cardPos);
            card.setPaintFront(true);
            card.setScale(0.8f);
            cardPos.x += X_CARD_OFFSET;
            cardPos.y += Y_CARD_OFFSET;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics graphicsContext) {
        cards.forEach(card -> card.render(gc, sb, graphicsContext));
    }
}
