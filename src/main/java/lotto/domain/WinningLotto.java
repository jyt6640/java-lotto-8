package lotto.domain;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int countMatches(Lotto lotto) {
        return (int) lotto.getNumbers()
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber(Lotto lotto) {
        return bonusNumber.isContainedIn(lotto);
    }

    public Rank determineRank(Lotto lotto) {
         int matchCount = countMatches(lotto);
         boolean hasBonus = hasBonusNumber(lotto);
         return Rank.of(matchCount, hasBonus);
    }
}
