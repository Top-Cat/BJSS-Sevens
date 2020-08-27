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
            // Play cards based on which paths allow us to play more of our other cards
            // if cards have equal value to us we prefer moves that don't give new moves to other players
            playable.sort(Comparator.comparingInt(this::valueScore).reversed().thenComparingInt(this::findStallingMove));
            table.place(takeCard(playable.get(0)));
        } else {
            skipMessage();
        }
    }

    private int valueScore(Card card) {
        // Find cards in our hand of the same suit and in the same direction as this card
        // there will only ever be one card in a direction that is playable
        int value = hand.stream()
                .filter(it -> it.suit == card.suit && (card.value == 7 || (it.value > 7) ^ (card.value < 7)))
                .mapToInt(it -> Math.abs(7 - it.value)).sum();

        // If the card's value is 7 we check both directions so normalise with every other
        // option where we only check in the direction of the card
        if (card.value == 7) {
            value /= 2;
        }

        return value;
    }

    private int findStallingMove(Card card) {
        Stream<Card> suit = hand.stream().filter(it -> it.suit == card.suit);

        // End cards don't can't be played on
        if (card.value == 1 || card.value == 13) {
            return 0;
        }

        // Find card where we have the next card in sequence to play as well
        if ((card.value == 7 && suit.filter(it -> it.value == 6 || it.value == 8).count() > 1) ||
            (card.value > 7 && suit.anyMatch(it -> it.value == card.value + 1)) ||
            (card.value < 7 && suit.anyMatch(it -> it.value == card.value - 1))) {
            return 1;
        }

        // Not a stalling move
        return 2;
    }
}
