package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public void printLotteriesPrefix(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLotteries (List<Integer> lotto) {
        System.out.println(lotto);
    }

    public void printStatistics(Map<Rank, Integer> ranks, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---");

        printRankResult(Rank.FIFTH, ranks);
        printRankResult(Rank.FOURTH, ranks);
        printRankResult(Rank.THIRD, ranks);
        printRankResult(Rank.SECOND, ranks);
        printRankResult(Rank.FIRST, ranks);

        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
    }

    private void printRankResult(Rank rank, Map<Rank, Integer> ranks) {
        int count = ranks.getOrDefault(rank, 0);
        System.out.println(rank.getMessage() + " - " + count + "개");
    }

}
