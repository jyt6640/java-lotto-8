package lotto.domain;

import static lotto.exception.ErrorMessage.INVALID_LOTTOS_SIZE;

import java.util.List;

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
}
