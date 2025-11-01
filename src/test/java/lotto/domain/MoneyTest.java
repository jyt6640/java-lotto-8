package lotto.domain;

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
}
