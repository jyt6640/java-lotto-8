package lotto.domain;

import static lotto.domain.exception.ErrorMessage.INVALID_MAX_MONEY;
import static lotto.domain.exception.ErrorMessage.INVALID_MIN_MONEY;
import static lotto.domain.exception.ErrorMessage.INVALID_MONEY_UNIT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @DisplayName("Money 정상 생성")
    @Test
    void Money_정상_생성() {
        //given
        int input = 6000;

        //when
        Money money = new Money(input);

        //then
        assertThat(money.calculateLottoCount()).isEqualTo(6);
    }

    @DisplayName("천원 미만으로 입력 시 예외 발생")
    @Test
    void 천원_미만으로_입력_시_예외_발생() {
        //given
        int input = 500;

        //when&then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MIN_MONEY.toString());
    }

    @DisplayName("십만원 이상 입력 시 예외 발생")
    @Test
    void 십만원_이상_입력_시_예외_발생() {
        //given
        int input = 150000;

        //when&then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MAX_MONEY.toString());
    }

    @DisplayName("천원 단위로 입력하지 않을 시 예외 발생")
    @Test
    void 천원_단위로_입력하지_않을_시_예외_발생() {
        //given
        int input = 1250;
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MONEY_UNIT.toString());

    }

    @DisplayName("수익률 계산")
    @Test
    void 수익률_계산() {
        //given
        int input = 50000;
        Money money = new Money(input);

        //when
        double result = money.calculateProfitRate(50000);

        //then
        assertThat(result).isEqualTo(100.0);
    }
}
