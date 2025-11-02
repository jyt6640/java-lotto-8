package lotto.common.config;

import lotto.service.mapper.OutputMapper;
import lotto.view.handler.InputHandler;
import lotto.controller.LottoController;
import lotto.view.handler.OutputHandler;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.NumberGenerator;
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
                lottoResultService(),
                outputMapper()
        );
    }

    public InputHandler inputHandler() {
        return new InputHandler(inputView());
    }

    public OutputHandler outputHandler() {
        return new OutputHandler(
                outputView()
        );
    }

    public OutputMapper outputMapper() {
        return new OutputMapper();
    }

    public LottoController lottoController() {
        return new LottoController(
                inputHandler(),
                outputHandler(),
                lottoGameService()
        );
    }
}