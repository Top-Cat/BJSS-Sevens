package uk.co.thomasc.bjss.player;

import org.junit.jupiter.api.Test;
import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Suit;
import uk.co.thomasc.bjss.game.Table;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LowestFirstPlayerTest {

    private final LowestFirstPlayer player = new LowestFirstPlayer();

    @Test
    public void testPlayedCard() {
        Table mockTable = mock(Table.class);

        player.giveCard(new Card(4, Suit.CLUBS));
        Card sevenOfHearts = new Card(7, Suit.HEARTS);
        player.giveCard(sevenOfHearts);
        player.giveCard(new Card(8, Suit.DIAMONDS));

        doReturn(true).when(mockTable).canPlace(any());

        player.takeTurn(mockTable);
        verify(mockTable).place(sevenOfHearts);
    }
}
