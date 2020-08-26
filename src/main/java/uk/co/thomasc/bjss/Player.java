package uk.co.thomasc.bjss;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> hand = new ArrayList<>();

    public void giveCard(Card card) {
        hand.add(card);
    }

    public boolean hasStartingCard() {
        return hand.stream().anyMatch(it -> it.value == 7 && it.suit == Suit.DIAMONDS);
    }

}
