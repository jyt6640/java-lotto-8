package lotto.dto;

import lotto.domain.Lottos;
import lotto.domain.Money;

public class LottoPurchaseResult {
    private final int count;
    private final Lottos lottos;
    private final Money money;

    public LottoPurchaseResult(int count, Lottos lottos, Money money) {
        this.count = count;
        this.lottos = lottos;
        this.money = money;
    }

    public int getCount() {
        return count;
    }

    public Lottos getLottos() {
        return lottos;
    }

    public Money getMoney() {
        return money;
    }
}
