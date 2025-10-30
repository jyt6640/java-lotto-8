package lotto.domain;

public enum Rank {
    FIRST(2_000_000_000L),
    SECOND(30_000_000L),
    THIRD(1_500_000L),
    FOURTH(50_000L),
    FIFTH(5_000L);

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
        if(matchCounts == 5) {
            return THIRD;
        }
        if(matchCounts == 4) {
            return FOURTH;
        }
        if(matchCounts == 3) {
            return FIFTH;
        }
        return null;
    }

    public long getPrize() {
        return prize;
    }
}
