package uk.co.thomasc.bjss.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PileTest {

    private final Pile pile = new Pile(Suit.DIAMONDS);

    @Test
    public void addTest() {
        Card sevenOfClubs = new Card(7, Suit.CLUBS);
        Card fiveOfDiamonds = new Card(5, Suit.DIAMONDS);
        Card sixOfDiamonds = new Card(6, Suit.DIAMONDS);
        Card sevenOfDiamonds = new Card(7, Suit.DIAMONDS);

        assertFalse(pile.canPlace(sevenOfClubs));
        assertThrows(RuntimeException.class, () -> {
            pile.place(sevenOfClubs);
        });

        assertFalse(pile.canPlace(sevenOfClubs));
        assertThrows(RuntimeException.class, () -> {
            pile.place(sixOfDiamonds);
        });

        assertTrue(pile.canPlace(sevenOfDiamonds));
        pile.place(sevenOfDiamonds);

        assertFalse(pile.canPlace(fiveOfDiamonds));
        assertThrows(RuntimeException.class, () -> {
            pile.place(fiveOfDiamonds);
        });

        assertTrue(pile.canPlace(sixOfDiamonds));
        pile.place(sixOfDiamonds);
    }

}
