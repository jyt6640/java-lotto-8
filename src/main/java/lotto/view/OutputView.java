package lotto.view;

import java.util.List;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public void printPurchaseLotto(List<List<Integer>> lotteries) {
        System.out.println(lotteries.size() + "개를 구매했습니다.");
        for (List<Integer> lotto : lotteries) {
            System.out.println(lotto);
        }
        System.out.println();
    }

}
