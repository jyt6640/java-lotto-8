package lotto.config;

import lotto.controller.InputController;
import lotto.controller.LottoController;
import lotto.service.PurchaseService;
import lotto.util.generator.LottoGenerator;
import lotto.util.generator.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public InputView inputView() {
        return new InputView();
    };

    public OutputView outputView() {
        return new OutputView();
    }

    public InputController inputController() {
        return new InputController(
                inputView(),
                outputView()
        );
    }

    public NumberGenerator numberGenerator() {
        return new LottoGenerator();
    }

    public LottoController lottoController() {
        return new LottoController(
                inputController(),
                outputView(),
                purchaseService()
        );
    }

    public PurchaseService purchaseService() {
        return new PurchaseService(
                numberGenerator()
        );
    }
}
