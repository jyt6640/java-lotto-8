package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(null, 6, false, 2_000_000_000L, "6개 일치 (%s원)"),
    SECOND(FIRST, 5, true, 30_000_000L, "5개 일치, 보너스 볼 일치 (%s원)"),
    THIRD(SECOND, 5, false, 1_500_000L, "5개 일치 (%s원)"),
    FOURTH(THIRD, 4, false, 50_000L, "4개 일치 (%s원)"),
    FIFTH(FOURTH, 3, false, 5_000L, "3개 일치 (%s원)"),
    NONE(FIFTH, 0, false, 0L, "꽝");

    private static final int BONUS_AVAILABLE_FROM_MATCH = 2;

    private Rank upperRank;
    private final int matchCount;
    private final boolean hasBonusNumber;
    private final long prize;
    private final String messageFormat;

    Rank(Rank upperRank, int matchCount, boolean hasBonusNumber, long prize, String messageFormat) {
        this.upperRank = upperRank;
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
        this.messageFormat = messageFormat;
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

    public String getMessage() {
        if (this == NONE) return messageFormat;
        String formattedPrize = String.format("%,d", prize);
        return String.format(messageFormat, formattedPrize);
    }

    public long getPrize() {
        return prize;
    }
}
