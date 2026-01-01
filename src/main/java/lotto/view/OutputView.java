package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_MESSAGE_PREFIX + e.getMessage());
        System.out.println();
    }

    public void printPurchaseLotto(List<List<Integer>> lotteries) {
        System.out.println(lotteries.size() + "개를 구매했습니다.");
        for (List<Integer> lotto : lotteries) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public void printStatistics(Map<Rank, Integer> rankCount, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---");

        printRankResult(Rank.FIFTH, rankCount);
        printRankResult(Rank.FOURTH, rankCount);
        printRankResult(Rank.THIRD, rankCount);
        printRankResult(Rank.SECOND, rankCount);
        printRankResult(Rank.FIRST, rankCount);

        System.out.printf("총 수익률은 %.1f%%입니다.", profitRate);
    }

    private void printRankResult(Rank rank, Map<Rank, Integer> rankCount) {
        int count = rankCount.getOrDefault(rank, 0);
        System.out.println(rank.getMessage() + " - " + count + "개");
    }
}
