package uk.co.thomasc.bjss.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Table {

    private final List<Pile> piles = new ArrayList<>();

    public Table() {
        for (Suit suit : Suit.values()) {
            piles.add(new Pile(suit));
        }
    }

    public boolean canPlace(Card card) {
        return piles.stream().anyMatch(it -> it.canPlace(card));
    }

    public void place(Card card) {
        Optional<Pile> openPile = piles.stream().filter(it -> it.canPlace(card)).findAny();
        openPile.ifPresent(pile -> pile.place(card));
    }

    public void reset() {
        for (Pile pile : piles) {
            pile.clear();
        }
    }

    public void printState() {
        System.out.println(piles.stream().map(it -> it.getHighCard() == null ? "--" : it.getHighCard().cardString()).collect(Collectors.joining(" | ")));
        // If low == high either both are null or both are a seven so we only print it at the top
        System.out.println(piles.stream().map(it -> it.getLowCard() == it.getHighCard() ? "--" : it.getLowCard().cardString()).collect(Collectors.joining(" | ")));
    }
}
