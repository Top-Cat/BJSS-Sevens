package uk.co.thomasc.bjss.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Players {

    private final List<Player> players = new ArrayList<>();
    private int index = 0;

    public Players() {
        players.add(new RandomPlayer());
        players.add(new HumanPlayer());
        players.add(new TacticalPlayer());
    }

    public Player get(int i) {
        return players.get(i);
    }

    public Player next() {
        index = (index + 1) % players.size();
        return peek();
    }

    public Player peek() {
        return players.get(index);
    }

    public void setStartingPlayer() {
        Optional<Player> startingPlayer = players.stream().filter(Player::hasStartingCard).findAny();
        startingPlayer.ifPresent(player -> index = players.indexOf(player));
    }

    public void clearHands() {
        for (Player player : players) {
            player.emptyHand();
        }
    }

    public void sortHands() {
        for (Player player : players) {
            player.sortHand();
        }
    }
}
