package uk.co.thomasc.bjss.player;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Table;

import java.util.Collections;
import java.util.List;

public class RandomPlayer extends Player {

    @Override
    public void takeTurn(Table table) {
        List<Card> playable = getPlayableCards(table);

        if (playable.size() > 0) {
            Collections.shuffle(playable);
            table.place(takeCard(playable.get(0)));
        } else {
            skipMessage();
        }
    }
}
