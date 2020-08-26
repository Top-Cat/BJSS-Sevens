package uk.co.thomasc.bjss.player;

import org.junit.jupiter.api.Test;
import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Suit;
import uk.co.thomasc.bjss.game.Table;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TacticalPlayerTest {

    private final TacticalPlayer player = new TacticalPlayer();

    @Test
    public void testPlayedCard() {
        Table mockTable = mock(Table.class);

        Card sixOfClubs = new Card(6, Suit.CLUBS);
        Card nineOfClubs = new Card(9, Suit.CLUBS);

        player.giveCard(new Card(7, Suit.HEARTS));
        player.giveCard(new Card(8, Suit.DIAMONDS));
        player.giveCard(new Card(10, Suit.CLUBS));
        player.giveCard(new Card(9, Suit.CLUBS));
        player.giveCard(new Card(6, Suit.CLUBS));
        player.giveCard(new Card(1, Suit.CLUBS));

        // Ace isn't playable
        doAnswer(it -> {
            Card c = (Card) it.getArguments()[0];
            return !(c.suit == Suit.CLUBS && c.value == 1);
        }).when(mockTable).canPlace(any());

        // 6 frees up the furthest card (the ace)
        player.takeTurn(mockTable);
        verify(mockTable).place(sixOfClubs);

        // 9 is a stalling move
        player.takeTurn(mockTable);
        verify(mockTable).place(nineOfClubs);
    }
}
