package lotto.domain;

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
        if (bonusNumber < 0 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 1~45 범위 값만 허용됩니다.");
        }
    }
}
