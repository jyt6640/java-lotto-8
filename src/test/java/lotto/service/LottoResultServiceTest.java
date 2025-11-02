package lotto.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.EnumMap;
import java.util.Map;
import lotto.domain.Money;
import lotto.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LottoResultServiceTest {
    private LottoResultService lottoResultService;

    @BeforeEach
    public void setUp(){
        lottoResultService = new LottoResultService();
    }

    @DisplayName("당첨 통계 기반으로 수익률 계산")
    @Test
    void 당첨_통계_기반으로_수익률_계산() {
        //given
        Money input = new Money(6 * 1000);

        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        statistics.put(Rank.FIRST, 1);
        statistics.put(Rank.SECOND, 1);
        statistics.put(Rank.THIRD, 1);
        statistics.put(Rank.FOURTH, 1);
        statistics.put(Rank.FIFTH, 1);
        statistics.put(Rank.NONE, 1);

        //when
        double result = lottoResultService.calculateProfitRate(statistics, input);

        //then
        assertThat(result).isEqualTo(33859250);
    }
}
