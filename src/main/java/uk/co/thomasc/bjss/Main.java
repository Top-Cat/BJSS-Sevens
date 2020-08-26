package uk.co.thomasc.bjss;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Suit;
import uk.co.thomasc.bjss.game.Table;
import uk.co.thomasc.bjss.player.Player;
import uk.co.thomasc.bjss.player.Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private final List<Card> deck = new ArrayList<>();
    private final Players players = new Players();
    private final Table table = new Table();

    private void testWinners() {
        Map<Player, AtomicInteger> wins = new HashMap<>();
        for (int k = 0; k < 50000; k++) {
            deal();

            while (true) {
                Player currentPlayer = players.next();
                currentPlayer.takeTurn(table);

                if (currentPlayer.hasWon()) {
                    wins.putIfAbsent(currentPlayer, new AtomicInteger());
                    wins.get(currentPlayer).addAndGet(1);
                    break;
                }
            }
        }

        for (Player player : wins.keySet()) {
            System.out.println(player.getClass().getName() + " : " + wins.get(player).get());
        }
    }

    private void deal() {
        players.clearHands();
        table.reset();

        Collections.shuffle(deck);

        for (int i = 0; i < 52; i++) {
            players.next().giveCard(deck.get(i));
        }

        players.sortHands();
        players.setStartingPlayer();
        players.peek().startGame(table);
    }

    private void play() {
        while (true) {
            Player currentPlayer = players.next();
            currentPlayer.takeTurn(table);

            if (currentPlayer.hasWon()) {
                System.out.println(currentPlayer.getClass().getName() + " has won");
                break;
            }
        }
    }

    public Main() {
        for (int i = 0; i < 52; i++) {
            deck.add(new Card((i % 13) + 1, Suit.values()[i / 13]));
        }

        deal();
        play();
    }

}
