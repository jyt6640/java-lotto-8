package lotto.view;

import java.util.List;

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
}
