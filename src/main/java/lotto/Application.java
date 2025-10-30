package lotto;

import lotto.domain.Lottos;
import lotto.service.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoMachine machine = new LottoMachine();

        String purchaseAmount = InputView.readPurchaseAmount();
        int amount = Integer.parseInt(purchaseAmount);
        Lottos Lottos = machine.purchase(amount);

        OutputView.printMyLottos(amount, Lottos);
    }
}
