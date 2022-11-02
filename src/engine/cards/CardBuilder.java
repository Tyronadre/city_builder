package engine.cards;

import eea.engine.event.Event;
import engine.interfaces.IGameBoard;
import engine.interfaces.IPlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CardBuilder {
    private String name;
    private int cost;
    private CardClass cardClass;
    private Type type;
    private Image back, front;
    private Event event;

    public static List<Card> buildCardsFromCSV(String csv) {
        List<Card> cards = new ArrayList<>();

        try (var reader = new BufferedReader(new FileReader(ResourceLoader.getResource(csv).getFile()))) {
            String l;
            while ((l = reader.readLine()) != null) {
                try {
                    var line = l.split(",");
                    if (line.length != 7) {
                        System.err.println("Could not parse line, skipping: " + Arrays.toString(line));
                        continue;
                    }
                    var cardBuilder = new CardBuilder();
                    cardBuilder.setName(line[0]);
                    cardBuilder.setType(switch (Integer.parseInt(line[1])) {
                        case 1 -> Type.PRIMARY_INDUSTRY;
                        case 2 -> Type.SECONDARY_INDUSTRY;
                        case 3 -> Type.RESTAURANTS;
                        case 4 -> Type.MAYOR_ESTABLISHMENT;
                        case 5 -> Type.LANDMARK;
                        default -> throw new IllegalStateException("Unexpected value: " + line[2]);
                    });
                    cardBuilder.setCardClass(switch (line[2]) {
                        case "feld" -> CardClass.WHEAT;
                        case "tier" -> CardClass.COW;
                        case "laden" -> CardClass.SUITCASE;
                        default -> throw new IllegalStateException("Unexpected value: " + line[2]);
                    });
                    cardBuilder.setCost(Integer.parseInt(line[3]));
                    cardBuilder.setFront(new Image("/assets/cards/" + line[4]));
                    cardBuilder.setBack(new Image("/assets/cards/" + line[5]));
                    cardBuilder.setEvent(getEvent(line[0]));
                    for (int i = 0; i < Integer.parseInt(line[6]); i++) {
                        cards.add(cardBuilder.build());
                    }
                } catch (RuntimeException | SlickException e) {
                    System.err.println("Failed to load card: " + l);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private static Event getEvent(String cardName) {
        return new Event("CardEvent_" + cardName) {
            @Override
            protected boolean performAction(GameContainer gc, StateBasedGame sb, int delta) {
                System.out.println("Card Event " + cardName + " triggered.");
                return false;
            }
        };

    }

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
