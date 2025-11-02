package lotto;

import lotto.common.config.AppConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        LottoController controller = config.lottoController();
        controller.run();
    }
}
