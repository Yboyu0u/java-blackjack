package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.machine.Card;
import blackjack.domain.machine.Score;

public abstract class Participant {
    private static final String NAME_ERROR = "[Error] 이름은 빈 값일 수 없습니다.";

    protected String name;
    protected List<Card> myCards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    private void validateName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(NAME_ERROR);
        }
    }

    public void addCard(Card card) {
        myCards.add(card);
    }

    public int score() {
        return Score.from(myCards)
                .getSum();
    }

    public boolean isBurst() {
        return score() > Score.CONDITION_BURST;
    }

    public boolean isBlackjack() {
        return score() == Score.CONDITION_BURST;
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public String getName() {
        return name;
    }
}
