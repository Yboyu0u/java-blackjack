package blackjack.controller;

import blackjack.domain.machine.Blackjack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Map;

public class BlackjackController {

    public void startGame() {
        Map<String, Long> playerNames = InputView.getPlayerNames();
        playerNames.keySet().forEach(name -> playerNames.put(name, InputView.getBettingMoney(name)));

        Dealer dealer = new Dealer();
        Players players = new Players(playerNames);
        Blackjack blackjack = new Blackjack();
        blackjack.dealInitialCards(dealer, players);

        OutputView.printInitStatus(dealer, players.getPlayers());
        progressGame(blackjack, players, dealer);
    }


    public void progressGame(Blackjack blackjack, Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            askDealCardToPlayer(blackjack, player);
        }

        askDealCardToDealer(blackjack, dealer);
        endGame(blackjack, dealer, players);
    }

    private void askDealCardToPlayer(Blackjack blackjack, Player player) {
        while (player.canDraw() && InputView.askAdditionalCard(player)) {
            blackjack.dealAdditionalCardToPlayer(player);
            OutputView.printCards(player);
        }
    }

    private void askDealCardToDealer(Blackjack blackjack, Dealer dealer) {
        while (dealer.canDraw()) {
            blackjack.dealAdditionalCardToDealer(dealer);
            OutputView.printDealerAdditionalCard();
        }
    }

    private void endGame(Blackjack blackjack, Dealer dealer, Players players) {
        OutputView.printCardsAndResult(dealer, players.getPlayers());
        OutputView.printRecord(blackjack.record(dealer, players));
        OutputView.printProfit(blackjack.profit(dealer, players));
    }
}