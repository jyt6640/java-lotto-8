package lotto.util;

import static lotto.exception.ErrorMessage.INVALID_INT_RANGE;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private final static String COMMA = ",";

    public static List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(COMMA))
                .map(String::strip)
                .map(Integer::parseInt)
                .toList();
    }

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_INT_RANGE.toString());
        }
    }
}
