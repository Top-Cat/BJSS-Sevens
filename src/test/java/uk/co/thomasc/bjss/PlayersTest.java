package uk.co.thomasc.bjss;

import org.junit.jupiter.api.Test;
import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Suit;
import uk.co.thomasc.bjss.player.Players;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersTest {

    private final Players players = new Players();

    @Test
    public void testSetStartingPlayer() {
        players.get(0).giveCard(new Card(4, Suit.CLUBS));
        players.get(1).giveCard(new Card(7, Suit.HEARTS));
        players.get(2).giveCard(new Card(7, Suit.DIAMONDS));

        players.setStartingPlayer();
        assertEquals(players.get(2), players.peek());

        players.get(2).emptyHand();

        players.setStartingPlayer();
        assertEquals(players.get(2), players.peek());

        players.get(0).giveCard(new Card(7, Suit.DIAMONDS));

        players.setStartingPlayer();
        assertEquals(players.get(0), players.peek());
    }
}
