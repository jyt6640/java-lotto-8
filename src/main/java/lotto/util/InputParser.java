package lotto.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .toList();
    }

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력값은 int 범위를 넘어갈 수 없습니다.");
        }
    }
}
