package lotto.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputHandlerTest {
    private InputHandler inputHandler;

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
        assertThatThrownBy(inputHandler::readPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 숫자만 입력 가능합니다.");

    }
}
