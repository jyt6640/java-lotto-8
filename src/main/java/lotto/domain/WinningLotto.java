package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new BonusNumber(bonusNumber);
        validateBonusNumber();
    }

    public int countMatches(Lotto lotto) {
        return (int) lotto.getNumbers()
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void validateBonusNumber() {
        if (bonusNumber.isContainedIn(winningNumbers)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public boolean hasBonusNumber(Lotto lotto) {
        return bonusNumber.isContainedIn(lotto);
    }
}
