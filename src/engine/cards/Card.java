package engine.cards;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import engine.interfaces.ICard;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// FIXME: 27.10.2022 Implementation

public abstract class Card extends Entity implements ICard {
    private final int cost;
    private final CardClass cardClass;
    private final Type type;
    private final ImageRenderComponent back, front;
    private boolean paintFront;

    public Card(String name, int cost, CardClass cardClass, Type type, Image back, Image front){
        super(name);
        this.cost = cost;
        this.cardClass = cardClass;
        this.type = type;
        this.back = new ImageRenderComponent(back);
        this.front = new ImageRenderComponent(front);
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
    public String toString() {
        return "Card{" +
                "cost=" + cost +
                ", cardClass=" + cardClass +
                ", type=" + type +
                ", back=" + back +
                ", front=" + front +
                ", paintFront=" + paintFront +
                '}';
    }
}