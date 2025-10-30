package lotto.domain;

public enum Rank {
    FIRST(2_000_000_000L);

    private final long prize;

    Rank(long prize) {
        this.prize = prize;
    }

    public static Rank of(int matchCounts, boolean hasBonusNumber) {
        if (matchCounts == 6) {
            return FIRST;
        }
        return null;
    }

    public long getPrize() {
        return prize;
    }
}
