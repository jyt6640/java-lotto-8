package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    NONE(0, false, 0L);

    private final int matchCount;
    private final boolean hasBonusNumber;
    private final long prize;

    Rank(int matchCount, boolean hasBonusNumber, long prize) {
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public static Rank of(int matchCounts, boolean hasBonusNumber) {
        Rank baseRank = Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCounts)
                .filter(rank -> !rank.hasBonusNumber || hasBonusNumber)
                .findFirst()
                .orElse(NONE);
        return baseRank;
    }

    public long getPrize() {
        return prize;
    }
}
