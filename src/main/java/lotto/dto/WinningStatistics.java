package lotto.dto;

public class WinningStatistics {
    private final String rankMessage;
    private final int count;

    public WinningStatistics(String rankMessage, int count) {
        this.rankMessage = rankMessage;
        this.count = count;
    }

    public String getRankMessage() {
        return rankMessage;
    }

    public int getCount() {
        return count;
    }
}
