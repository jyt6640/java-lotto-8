package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.util.LottoNumberGenerator;

public class LottoMachine {
    public Lottos purchase(int amount) {
        int count = calculateLottoCount(amount);
        return generateLottos(count);
    }

    private int calculateLottoCount(int amount) {
        return amount / 1000;
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
