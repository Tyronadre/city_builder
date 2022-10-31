package engine.cards;

import eea.engine.event.Event;
import engine.interfaces.IGameBoard;
import engine.interfaces.IPlayer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CardBuilder {
    private String name;
    private int cost;
    private CardClass cardClass;
    private Type type;
    private Image back, front;
    private Event event;


    public Card build() throws SlickException {
        return new Card(name, cost, cardClass, type, back, front) {
            @Override
            public Event getEvent(IGameBoard gameBoard, IPlayer player) {
                return event;
            }
        };
    }

    public CardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CardBuilder setFront(Image front) {
        this.front = front;
        return this;
    }

    public CardBuilder setBack(Image back) {
        this.back = back;
        return this;
    }

    public CardBuilder setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public CardBuilder setCardClass(CardClass cardClass) {
        this.cardClass = cardClass;
        return this;
    }

    public CardBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    public CardBuilder setEvent(Event event) {
        this.event = event;
        return this;
    }
}
