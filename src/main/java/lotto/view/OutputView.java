package lotto.view;

import java.util.List;
import lotto.dto.WinningStatistics;

public class OutputView {
    public void printMyLottos(int purchaseCount, List<List<Integer>> lottoNumbers) {
        System.out.println(purchaseCount + "개를 구매했습니다.");
        lottoNumbers.forEach(System.out::println);
    }

    public void printResult(List<WinningStatistics> results, double profitRate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        results.forEach(result ->
                System.out.println(result.getRankMessage() + " - " + result.getCount() + "개")
        );

        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    public void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
