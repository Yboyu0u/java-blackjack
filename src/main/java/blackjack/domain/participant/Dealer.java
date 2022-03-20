package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canDraw() {
        return isHit() && !isBlackjack();
    }
}
