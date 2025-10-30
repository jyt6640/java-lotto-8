package lotto.util;

import java.util.Arrays;
import java.util.List;

public class WinningNumberParser {
    public static int extractBonusNumber(List<Integer> getWinningNumbers) {
        return getWinningNumbers.getLast();
    }

    public static List<Integer> extractWinningNumbers(List<Integer> getWinningNumbers) {
        return getWinningNumbers.subList(0, 6);
    }

    public static List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .toList();
    }

    public static int parseBonusNumber(String input) {
        return Integer.parseInt(input.strip());
    }
}
