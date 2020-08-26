package uk.co.thomasc.bjss.player;

import uk.co.thomasc.bjss.game.Card;
import uk.co.thomasc.bjss.game.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HumanPlayer extends Player {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void takeTurn(Table table) {
        table.printState();

        System.out.println(hand.stream().map(it -> String.format("%d) %s", hand.indexOf(it), table.canPlace(it) ? (ANSI_GREEN + it.cardString() + ANSI_RESET) : it.cardString())).collect(Collectors.joining(", ")));

        System.out.print("\nPick a card: ");
        try {
            int i = Integer.parseInt(br.readLine());
            Card toPlace = hand.get(i);
            if (table.canPlace(toPlace)) {
                table.place(takeCard(toPlace));
            } else {
                skipMessage();
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
