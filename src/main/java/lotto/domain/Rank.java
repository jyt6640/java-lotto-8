package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(null, 6, false, 2_000_000_000),
    SECOND(FIRST, 5, true, 30_000_000),
    THIRD(SECOND, 5, false, 1_500_000),
    FOURTH(THIRD, 4, false, 50_000),
    FIFTH(FOURTH, 3, false, 5_000),
    NONE(FIFTH, 0, false, 0);

    private Rank upperRank;
    private int matchCount;
    private boolean hasBonusNumber;
    private long prize;

    Rank(Rank upperRank, int matchCount, boolean hasBonusNumber, long prize) {
        this.upperRank = upperRank;
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
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

    public long getPrize() {
        return prize;
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

    public String getMessage() {
        if (this == SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 (%,d원)", matchCount, prize);
        }
        return String.format("%d개 일치 (%,d원)", matchCount, prize);
    }
}
