package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.service.dto.LottoPurchaseResult;
import lotto.util.LottoNumberGenerator;

public class LottoPurchaseService {
    public LottoPurchaseResult purchase(int amount) {
        Money money = new Money(amount);
        int count = money.calculateLottoCount();
        Lottos lottos = generateLottos(count);
        return new LottoPurchaseResult(count, lottos, money);
    }

    private Lottos generateLottos(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(generateLotto());
        }
        return new Lottos(lottoList);
    }

    private Lotto generateLotto() {
        List<Integer> lottoNumbers = LottoNumberGenerator.getLotto();
        return new Lotto(lottoNumbers);
    }
}
