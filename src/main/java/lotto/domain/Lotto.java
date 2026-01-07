package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int compareWinningLotto(Lotto winningLotto) {
        int matchingCount = 0;
        for (int number : winningLotto.getNumbers()) {
            if (numbers.contains(number)) {
                matchingCount++;
            }
        }
        return matchingCount;
    }

    public boolean hasBonusNumber(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.getBonusNumber());
    }
}
