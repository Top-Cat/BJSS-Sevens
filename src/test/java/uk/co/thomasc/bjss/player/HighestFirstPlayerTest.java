package uk.co.thomasc.bjss.player;

import org.junit.jupiter.api.Test;
import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Suit;
import uk.co.thomasc.bjss.game.Table;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HighestFirstPlayerTest {

    private final HighestFirstPlayer player = new HighestFirstPlayer();

    @Test
    public void testPlayedCard() {
        Table mockTable = mock(Table.class);

        Card fourOfClubs = new Card(4, Suit.CLUBS);
        player.giveCard(fourOfClubs);
        player.giveCard(new Card(7, Suit.HEARTS));
        player.giveCard(new Card(8, Suit.DIAMONDS));

        doReturn(true).when(mockTable).canPlace(any());

        player.takeTurn(mockTable);
        verify(mockTable).place(fourOfClubs);
    }
}
