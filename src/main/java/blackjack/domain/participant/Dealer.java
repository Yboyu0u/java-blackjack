package blackjack.domain.participant;

import blackjack.domain.machine.Card;
import blackjack.domain.machine.Cards;
import blackjack.domain.strategy.NumberGenerator;

public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final int CONDITION_HIT = 16;
    private final Cards cards;

    public Dealer() {
        super(NAME);
        cards = new Cards();
    }

    public Card handOutCard(NumberGenerator numberGenerator) {
        return cards.pickCard(numberGenerator);
    }

    public boolean isHit() {
        return score() <= CONDITION_HIT;
    }
}
