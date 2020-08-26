package uk.co.thomasc.bjss.player;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Table;

import java.util.Comparator;
import java.util.List;

public class TacticalPlayer extends Player {

    @Override
    public void takeTurn(Table table) {
        List<Card> playable = getPlayableCards(table);

        if (playable.size() > 0) {
            playable.sort(Comparator.comparingInt(this::valueScore).reversed());
            table.place(takeCard(playable.get(0)));
        }
    }

    private int valueScore(Card card) {
        int value = hand.stream().filter(it -> it.suit == card.suit && (it.value == 7 || (it.value > 7) ^ (card.value < 7))).mapToInt(it -> Math.abs(7 - it.value)).sum();

        if (card.value == 7) {
            value /= 2;
        }

        return value;
    }
}
