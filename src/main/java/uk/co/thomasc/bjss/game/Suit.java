package uk.co.thomasc.bjss.game;

public enum Suit {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠");

    public String suitChar;

    Suit(String s) {
        suitChar = s;
    }
}
