package lotto.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputHandlerTest {
    private InputHandler inputHandler;

    private void errorThrowsTest(Runnable runnable, String message) {
        assertThatThrownBy(runnable::run)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }

    @DisplayName("구매 금액 문자열 입력 시 예외 발생")
    @Test
    void 구매_금액_문자열_입력_시_예외_발생() {
        //given
        InputView inputView = new InputView() {
            @Override
            public String readPurchaseAmount() {
                return "1000;";
            }
        };
        inputHandler = new InputHandler(inputView);

        //when&then
        errorThrowsTest(inputHandler::readPurchaseAmount, "[ERROR] 숫자만 입력 가능합니다.");
    }

    @DisplayName("보너스 번호 문자열 입력 시 예외 발생")
    @Test
    void 보너스_번호_문자열_입력_시_예외_발생() {
        //given
        InputView inputView = new InputView() {
            @Override
            public String readBonusNumber() {
                return "av";
            }
        };
        inputHandler = new InputHandler(inputView);

        //when&then
        errorThrowsTest(inputHandler::readBonusNumber, "[ERROR] 숫자만 입력 가능합니다.");
    }

    @DisplayName("구입 금액 입력 시 공백 입력은 예외 발생")
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\r", "\r\n"})
    @ParameterizedTest
    void 구입금액_공백_입력_시_예외(String input) {
        //given
        InputView inputView = new InputView() {
            @Override
            public String readPurchaseAmount() {
                return input;
            }
        };
        inputHandler = new InputHandler(inputView);

        //when&then
        errorThrowsTest(inputHandler::readPurchaseAmount, "[ERROR] 입력값은 공백일 수 없습니다.");
    }

    @DisplayName("당첨 번호 입력 시 공백 입력은 예외 발생")
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\r", "\r\n"})
    @ParameterizedTest
    void 당첨번호_공백_입력_시_예외(String input) {
        //given
        InputView inputView = new InputView() {
            @Override
            public String readWinningLottoNumbers() {
                return input;
            }
        };
        inputHandler = new InputHandler(inputView);

        // when&then
        errorThrowsTest(inputHandler::readWinningNumbers, "[ERROR] 입력값은 공백일 수 없습니다.");
    }

    @DisplayName("보너스 번호 입력 시 공백 입력은 예외 발생")
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\r", "\r\n"})
    @ParameterizedTest
    void 보너스번호_공백_입력_시_예외(String input) {
        //given
        InputView inputView = new InputView() {
            @Override
            public String readBonusNumber() {
                return input;
            }
        };
        inputHandler = new InputHandler(inputView);

        //when&then
        errorThrowsTest(inputHandler::readBonusNumber, "[ERROR] 입력값은 공백일 수 없습니다.");
    }
}
