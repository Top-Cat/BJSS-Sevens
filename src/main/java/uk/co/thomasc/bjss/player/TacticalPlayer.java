package uk.co.thomasc.bjss.player;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Table;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TacticalPlayer extends Player {

    @Override
    public void takeTurn(Table table) {
        List<Card> playable = getPlayableCards(table);

        if (playable.size() > 0) {
            playable.sort(Comparator.comparingInt(this::valueScore).reversed().thenComparingInt(this::findStallingMove));
            table.place(takeCard(playable.get(0)));
        } else {
            skipMessage();
        }
    }

    private int valueScore(Card card) {
        int value = hand.stream().filter(it -> it.suit == card.suit && (it.value == 7 || (it.value > 7) ^ (card.value < 7))).mapToInt(it -> Math.abs(7 - it.value)).sum();

        if (card.value == 7) {
            value /= 2;
        }

        return value;
    }

    private int findStallingMove(Card card) {
        Stream<Card> suit = hand.stream().filter(it -> it.suit == card.suit);

        if (card.value == 1 || card.value == 13) {
            return 0;
        }

        if (card.value == 7) {
            if (suit.filter(it -> it.value == 6 || it.value == 8).count() > 1) {
                return 1;
            }
        } else if (card.value > 7) {
            if (suit.anyMatch(it -> it.value == card.value + 1)) {
                return 1;
            }
        } else if (suit.anyMatch(it -> it.value == card.value - 1)) {
            return 1;
        }

        return 2;
    }
}
