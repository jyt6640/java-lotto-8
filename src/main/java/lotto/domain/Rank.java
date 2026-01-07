package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST(null, 6, false, 2_000_000_000L),
    SECOND(FIRST, 5, true, 30_000_000L),
    THIRD(SECOND, 5, false, 1_500_000L),
    FOURTH(THIRD, 4, false, 50_000L),
    FIFTH(FOURTH, 3, false, 5_000L),
    SIXTH(FIFTH, 2, false, 0L),
    NONE(SIXTH, 0, false, 0L);

    private final Rank upperRank;
    private final int matchCount;
    private final boolean hasBonusNumber;
    private final long prize;

    Rank(Rank upperRank, int matchCount, boolean hasBonusNumber, long prize) {
        this.upperRank = upperRank;
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean hasBonusNumber) {
        Rank baseRank = fromMatchCount(matchCount);

        if (hasBonusNumber && baseRank.canUpgrade()) {
            return baseRank.upgrade();
        }
        return baseRank;
    }

    private static Rank fromMatchCount(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> !rank.hasBonusNumber)
                .findFirst()
                .orElse(NONE);
    }

    private boolean canUpgrade() {
        return this != FIRST && this != SECOND && this != NONE;
    }

    private Rank upgrade() {
        return upperRank;
    }

    public long getPrize() {
        return prize;
    }

    public String getMessage() {
        if (this == SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 (%,d원)", matchCount, prize);
        }
        return String.format("%d개 일치 (%,d원)", matchCount, prize);
    }
}
