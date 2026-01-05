package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.util.generator.NumberGenerator;

public class PurchaseService {

    private NumberGenerator generator;

    public PurchaseService(NumberGenerator generator) {
        this.generator = generator;
    }

    public List<Lotto> generateLotto(Money money) {
        List<Lotto> lotteries = new ArrayList<>();
        for (int i = 0; i < money.getCount(); i++) {
            lotteries.add(generator.generate());
        }
        return lotteries;
    }
}
