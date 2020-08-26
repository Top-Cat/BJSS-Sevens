package uk.co.thomasc.bjss.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    public void cardStringTest() {
        assertEquals("K♣", new Card(13, Suit.CLUBS).cardString());
        assertEquals("5♦", new Card(5, Suit.DIAMONDS).cardString());
    }

}
