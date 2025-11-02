package lotto.controller;

import static lotto.domain.exception.ErrorMessage.INVALID_INPUT_NOT_BLANK;
import static lotto.domain.exception.ErrorMessage.INVALID_INPUT_ONLY_NUMBER;
import static lotto.domain.exception.ErrorMessage.INVALID_WINNING_NUMBER_FORMAT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.view.InputView;
import lotto.view.handler.InputHandler;
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
        errorThrowsTest(inputHandler::readPurchaseAmount, INVALID_INPUT_ONLY_NUMBER.toString());
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
        errorThrowsTest(inputHandler::readBonusNumber, INVALID_INPUT_ONLY_NUMBER.toString());
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
        errorThrowsTest(inputHandler::readPurchaseAmount, INVALID_INPUT_NOT_BLANK.toString());
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
        errorThrowsTest(inputHandler::readWinningNumbers, INVALID_INPUT_NOT_BLANK.toString());
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
        errorThrowsTest(inputHandler::readBonusNumber, INVALID_INPUT_NOT_BLANK.toString());
    }

    @DisplayName("당첨 번호 입력 시 올바른 형식이 아니면 예외 발생")
    @ValueSource(strings = {
            "1 2 3 4 5 6",
            "1,2,3,4,5,",
            ",1,2,3,4,5",
            "1,,2,3,4,5",
            "1,2,3,a,5,6",
            "1,2,3,4,5,6,",
            "1, 2, ,3,4,5",
            "1,2,3,4,5.6"
    })
    @ParameterizedTest
    void 당첨_번호_입력_시_올바른_형식이_아니면_예외_발생(String input) {
        // given
        InputView inputView = new InputView() {
            @Override
            public String readWinningLottoNumbers() {
                return input;
            }
        };
        inputHandler = new InputHandler(inputView);

        // when & then
        errorThrowsTest(inputHandler::readWinningNumbers, INVALID_WINNING_NUMBER_FORMAT.toString());
    }
}
