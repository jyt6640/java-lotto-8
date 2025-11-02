package lotto.dto.mapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;
import lotto.dto.WinningStatistics;

public class OutputMapper {
    public List<WinningStatistics> mapToWinningStatistics(Map<Rank, Integer> statistics) {
        return statistics.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Rank.NONE)
                .map(entry -> new WinningStatistics(
                        entry.getKey()
                                .getMessage(),
                        entry.getValue()
                ))
                .sorted(Comparator.comparing(WinningStatistics::getRankMessage))
                .toList();
    }
}
