package uk.co.thomasc.bjss.game;

import java.util.Objects;

public class Card {
    public final int value;
    public final Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public String valueString() {
        switch (value) {
            case 13:
                return "K";
            case 12:
                return "Q";
            case 11:
                return "J";
            case 1:
                return "A";
            default:
                return String.valueOf(value);
        }
    }

    public String cardString() {
        return String.format("%s%s", valueString(), suit.suitChar);
    }

    @Override
    public String toString() {
        return String.format("%s%s", valueString(), suit.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value &&
                suit == card.suit;
    }
}
