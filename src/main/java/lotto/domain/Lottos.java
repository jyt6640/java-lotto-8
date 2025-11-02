package lotto.domain;

import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateEmptyLotto(lottos);
        this.lottos = List.copyOf(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void validateEmptyLotto(List<Lotto> lottos) {
        if (lottos.size() == 0) {
            throw new IllegalArgumentException("[ERROR] 로또는 하나 이상 생성해야 합니다.");
        }
    }
}
