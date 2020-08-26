package uk.co.thomasc.bjss.game;

public class Pile {

    public final Suit suit;

    private Card highCard;
    private Card lowCard;

    public Pile(Suit suit) {
        this.suit = suit;
    }

    public Card getHighCard() {
        return highCard;
    }

    public Card getLowCard() {
        return lowCard;
    }

    public boolean canPlace(Card card) {
        if (card.suit != suit) {
            return false;
        }

        // Piles start with a 7 of the correct suit
        return (card.value == 7) ||
            (lowCard != null && card.value + 1 == lowCard.value) ||
            (highCard != null && card.value - 1 == highCard.value);
    }

    public void place(Card card) {
        if (!canPlace(card)) {
            throw new RuntimeException("Can't place that card on this pile");
        }

        if (card.value == 7) {
            highCard = lowCard = card;
        } else if (lowCard != null && card.value + 1 == lowCard.value) {
            lowCard = card;
        } else if (highCard != null && card.value - 1 == highCard.value) {
            highCard = card;
        }
    }

    public void clear() {
        highCard = null;
        lowCard = null;
    }
}
