package engine.cards;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import engine.interfaces.ICard;
import engine.interfaces.IGameBoard;
import engine.interfaces.IPlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

// FIXME: 27.10.2022 Implementation

public class Card extends Entity implements ICard {
    private final int cost;
    private final CardClass cardClass;
    private final Type type;
    private final ImageRenderComponent back, front;
    private boolean paintFront;

    public Card(String name, int cost, CardClass cardClass, Type type, Image back, Image front) throws SlickException {
        super(name);
        this.cost = cost;
        this.cardClass = cardClass;
        this.type = type;
        this.back = new ImageRenderComponent(new Image("/assets/cards/" + name + "_front.png"));
        this.front = new ImageRenderComponent(new Image("/assets/cards/default_back.png"));
        paintFront = false;
        addComponent(this.back);
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public CardClass getCardClass() {
        return cardClass;
    }

    @Override
    public void setPaintFront(boolean paintFront) {
        this.paintFront = paintFront;
        removeComponent(paintFront ? back : front);
        addComponent(paintFront ? front : back);
    }

    @Override
    public Event getEvent(IGameBoard gameBoard, IPlayer player) {
        return new Event() {
            @Override
            protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
                return false;
            }
        };
    }
}
