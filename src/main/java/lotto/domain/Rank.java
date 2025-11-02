package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(null, 6, false, 2_000_000_000L),
    SECOND(FIRST, 5, true, 30_000_000L),
    THIRD(SECOND, 5, false, 1_500_000L),
    FOURTH(THIRD, 4, false, 50_000L),
    FIFTH(FOURTH, 3, false, 5_000L),
    NONE(FIFTH, 0, false, 0L);

    private static final int BONUS_AVAILABLE_FROM_MATCH = 2;

    private Rank upperRank;
    private final int matchCount;
    private final boolean hasBonusNumber;
    private final long prize;

    Rank(Rank upperRank, int matchCount, boolean hasBonusNumber, long prize) {
        this.upperRank = upperRank;
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public static Rank of(int matchCounts, boolean hasBonusNumber) {
        Rank baseRank = fromMatchCount(matchCounts, hasBonusNumber);
        if (hasBonusNumber && matchCounts >= BONUS_AVAILABLE_FROM_MATCH) {
            return baseRank.upgrade();
        }
        return baseRank;
    }

    private static Rank fromMatchCount(int matchCount, boolean hasBonusNumber) {
        return Arrays.stream(values())
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

    public long getPrize() {
        return prize;
    }
}
