package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(null, 6, false),
    SECOND(FIRST, 5, true),
    THIRD(SECOND, 5, false),
    FOURTH(THIRD, 4, false),
    FIFTH(FOURTH, 3, false),
    NONE(FIFTH, 0, false);

    private Rank upperRank;
    private int matchCount;
    private boolean hasBonusNumber;

    Rank(Rank upperRank, int matchCount, boolean hasBonusNumber) {
        this.upperRank = upperRank;
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static Rank of(int matchCount, boolean hasBonusNumber) {
        Rank baseRank = fromMatchCount(matchCount, hasBonusNumber);
        if (hasBonusNumber) {
            if (matchCount >= 2) {
                return baseRank.upgrade();
            }
        }
        return baseRank;
    }

    public static Rank fromMatchCount(int matchCount, boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> !rank.hasBonusNumber || hasBonusNumber)
                .findFirst()
                .orElse(NONE);
    }

    private Rank upgrade() {
        if (this == SECOND) {
            return this;
        }
        return upperRank;
    }
}
