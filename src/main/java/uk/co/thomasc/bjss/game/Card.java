package uk.co.thomasc.bjss.game;

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
}
