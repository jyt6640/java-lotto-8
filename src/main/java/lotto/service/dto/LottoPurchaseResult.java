package lotto.service.dto;

import lotto.domain.Lottos;

public class LottoPurchaseResult {
    private final int count;
    private final Lottos lottos;

    public LottoPurchaseResult(int count, Lottos lottos) {
        this.count = count;
        this.lottos = lottos;
    }

    public int getCount() {
        return count;
    }

    public Lottos getLottos() {
        return lottos;
    }
}
