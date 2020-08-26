package uk.co.thomasc.bjss.player;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Table;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighestFirstPlayer extends Player {

    @Override
    public void takeTurn(Table table) {
        List<Card> playable = getPlayableCards(table);

        if (playable.size() > 0) {
            playable.sort(Comparator.comparingInt(o -> Math.abs(7 - ((Card) o).value)).reversed());
            table.place(takeCard(playable.get(0)));
        }
    }
}
