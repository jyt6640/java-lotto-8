// lotto/config/AppConfig.java
package lotto.config;

import lotto.controller.InputHandler;
import lotto.controller.LottoController;
import lotto.controller.OutputHandler;
import lotto.generator.LottoNumberGenerator;
import lotto.generator.NumberGenerator;
import lotto.service.LottoGameService;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public NumberGenerator numberGenerator() {
        return new LottoNumberGenerator();
    }

    public LottoPurchaseService lottoPurchaseService() {
        return new LottoPurchaseService(numberGenerator());
    }

    public LottoResultService lottoResultService() {
        return new LottoResultService();
    }

    public LottoGameService lottoGameService() {
        return new LottoGameService(
                lottoPurchaseService(),
                lottoResultService()
        );
    }

    public InputHandler inputHandler() {
        return new InputHandler(inputView());
    }

    public OutputHandler outputHandler() {
        return new OutputHandler(outputView());
    }

    public LottoController lottoController() {
        return new LottoController(
                inputHandler(),
                outputHandler(),
                lottoGameService()
        );
    }
}