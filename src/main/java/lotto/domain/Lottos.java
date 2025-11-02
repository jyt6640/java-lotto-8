package lotto.domain;

import static lotto.domain.exception.ErrorMessage.INVALID_LOTTOS_SIZE;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private static final int EMPTY_SIZE = 0;

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateEmptyLotto(lottos);
        this.lottos = List.copyOf(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void validateEmptyLotto(List<Lotto> lottos) {
        if (lottos.size() == EMPTY_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTOS_SIZE.toString());
        }
    }

    public List<List<Integer>> toNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }

    public Map<Rank, Integer> calculateStatistics(WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = initializeStatistics();
        for (Lotto lotto : this.lottos) {
            Rank rank = winningLotto.determineRank(lotto);
            statistics.computeIfPresent(rank, (key, value) -> value + 1);
        }
        return statistics;
    }

    private Map<Rank, Integer> initializeStatistics() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                 statistics.put(rank, 0);
             }
        }
        return statistics;
    }

}
