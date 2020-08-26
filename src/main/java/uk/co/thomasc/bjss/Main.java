package uk.co.thomasc.bjss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private final List<Card> deck = new ArrayList<>();
    private final Players players = new Players();

    private void deal() {
        for (int i = 0; i < 52; i++) {
            deck.add(new Card((i % 13) + 1, Suit.values()[i / 13]));
        }

        Collections.shuffle(deck);

        for (int i = 0; i < 52; i++) {
            players.next().giveCard(deck.get(i));
        }

        players.setStartingPlayer();
        System.out.println("Hi");
    }

    public Main() {
        deal();
    }

}
