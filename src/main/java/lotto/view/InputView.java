package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String value = readLine();
        System.out.println();
        return value;
    }

    private String readLine() {
        return Console.readLine();
    }
}
