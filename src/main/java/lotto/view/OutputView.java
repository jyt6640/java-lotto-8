package lotto.view;

import java.util.List;
import lotto.dto.WinningStatistics;

public class OutputView {
    private static final String BUY_OUTPUT = "개를 구매했습니다.";
    private static final String WINNING_STATISTICS_PREFIX = "당첨 통계";
    private static final String HYPHEN = " - ";
    private static final String COUNT = "개";
    private static final String DIVIDER = "---";
    private static final String PROFIT_RATE_RESULT = "총 수익률은 %.1f%%입니다.%n";

    public void printMyLottos(int purchaseCount, List<List<Integer>> lottoNumbers) {
        System.out.println();
        System.out.println(purchaseCount + BUY_OUTPUT);
        lottoNumbers.forEach(System.out::println);
    }

    public void printResult(List<WinningStatistics> results, double profitRate) {
        System.out.println();
        System.out.println(WINNING_STATISTICS_PREFIX);
        System.out.println(DIVIDER);

        results.forEach(result ->
                System.out.println(result.getRankMessage() + HYPHEN + result.getCount() + COUNT)
        );

        System.out.printf(PROFIT_RATE_RESULT, profitRate);
    }

    public void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
