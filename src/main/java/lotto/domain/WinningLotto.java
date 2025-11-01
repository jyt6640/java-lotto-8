package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int countMatches(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber() {
        return winningNumbers.contains(bonusNumber);
    }
}
