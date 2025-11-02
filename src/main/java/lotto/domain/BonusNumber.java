package lotto.domain;

import static lotto.domain.exception.ErrorMessage.DUPLICATE_BONUS_NUMBER;
import static lotto.domain.exception.ErrorMessage.INVALID_NUMBER_RANGE;

public class BonusNumber {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;

    private final int bonusNumber;

    public BonusNumber(int bonusNumber, Lotto winningNumbers) {
        validate(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber, Lotto winningNumbers) {
        validateRange(bonusNumber);
        validateDuplicate(bonusNumber, winningNumbers);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < MIN_RANGE || bonusNumber > MAX_RANGE) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.toString());
        }
    }

    private void validateDuplicate(int bonusNumber, Lotto winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER.toString());
        }
    }

    public boolean isContainedIn(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
