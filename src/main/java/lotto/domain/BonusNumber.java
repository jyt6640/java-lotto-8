package lotto.domain;

import java.util.List;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber) {
        validateRange(bonusNumber);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 1~45 범위 값만 허용됩니다.");
        }
    }

    public boolean isDuplicatedIn(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }

    public boolean isContainedIn(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
