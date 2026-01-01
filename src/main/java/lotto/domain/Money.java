package lotto.domain;

public class Money {

    private int money;

    public Money(int money) {
        this.money = money;
    }

    public int purchaseCount() {
        return money/1000;
    }
}
