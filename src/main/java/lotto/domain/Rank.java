package lotto.domain;

public enum Rank {
    FIRST(2_000_000_000L),
    SECOND(30_000_000L);

    private final long prize;

    Rank(long prize) {
        this.prize = prize;
    }

    public static Rank of(int matchCounts, boolean hasBonusNumber) {
        if (matchCounts == 6) {
            return FIRST;
        }
        if(matchCounts == 5 && hasBonusNumber) {
            return SECOND;
        }
        return null;
    }

    public long getPrize() {
        return prize;
    }
}
