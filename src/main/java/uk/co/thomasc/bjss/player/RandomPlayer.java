package uk.co.thomasc.bjss.player;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Table;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {

    private final Random random = new Random();

    @Override
    public void takeTurn(Table table) {
        List<Card> playable = getPlayableCards(table);

        if (playable.size() > 0) {
            // Pick a card at random
            table.place(takeCard(
                    playable.get(random.nextInt(playable.size()))
            ));
        } else {
            skipMessage();
        }
    }
}
