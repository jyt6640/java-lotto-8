package Util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomUtil {
    private static final int DEFAULT_COUNT = 6;
    private static final int WINNING_NUMBER_COUNT = 7;

    public static List<Integer> getLottoNumber(int count) {
        return Randoms.pickUniqueNumbersInRange(1,45, count);
    }

    public static List<Integer> getLotto() {
        return getLottoNumber(DEFAULT_COUNT);
    }

    public static List<Integer> getWinningNumbers(int count) {
        return Randoms.pickUniqueNumbersInRange(1,45, count);
    }

    public static List<Integer> getWinningLotto() {
        return getWinningNumbers(WINNING_NUMBER_COUNT);
    }

    public static int extractBonusNumber(List<Integer> getWinningNumbers) {
        return getWinningNumbers.getLast();
    }
}
