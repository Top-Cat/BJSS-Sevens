package uk.co.thomasc.bjss.player;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Suit;
import uk.co.thomasc.bjss.game.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Player {

    protected final List<Card> hand = new ArrayList<>();

    public void giveCard(Card card) {
        hand.add(card);
    }

    public Card takeCard(Card card) {
        hand.remove(card);
        return card;
    }

    public void emptyHand() {
        hand.clear();
    }

    public boolean hasStartingCard() {
        return hand.stream().anyMatch(it -> it.value == 7 && it.suit == Suit.DIAMONDS);
    }

    protected List<Card> getPlayableCards(Table table) {
        return hand.stream().filter(table::canPlace).collect(Collectors.toList());
    }

    public abstract void takeTurn(Table table);

    public boolean hasWon() {
        return hand.size() == 0;
    }
}
