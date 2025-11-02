package lotto.view;

import java.util.Map;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {
    public void printMyLottos(int purchaseCount, Lottos lottos) {
        System.out.println(purchaseCount + "개를 구매했습니다.");
        lottos.getLottos()
                .forEach(lotto ->
                    System.out.println(lotto.getNumbers())
                );
    }

    public void printResult(Map<Rank, Integer> statistics, double profitRate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + statistics.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + statistics.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + statistics.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + statistics.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + statistics.get(Rank.FIRST) + "개");
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    public void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
