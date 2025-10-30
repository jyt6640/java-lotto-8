package lotto.view;

import lotto.domain.Lottos;

public class OutputView {
    private OutputView() {
    }

    public static void printMyLottos(int amount, Lottos lottos) {
        int count = amount / 1000;
        System.out.println(count + "개를 구매했습니다.");
        lottos.getLottos().forEach(lotto ->
            System.out.println(lotto.getNumbers())
        );
    }
}
