package Util;

import java.util.List;

public class WinningNumberExtractor {
    public static int extractBonusNumber(List<Integer> getWinningNumbers) {
        return getWinningNumbers.getLast();
    }

    public static List<Integer> extractWinningNumbers(List<Integer> getWinningNumbers) {
        return getWinningNumbers.subList(0, 6);
    }
}
