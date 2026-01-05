package lotto.config;

import lotto.controller.InputController;
import lotto.controller.LottoController;
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

    public LottoController lottoController() {
        return new LottoController(
                inputController(),
                outputView()
        );
    }
}
