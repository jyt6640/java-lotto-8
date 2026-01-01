package lotto.domain;

public class Money {

    public int getMoney() {
        return money;
    }

    private int money;

    public Money(int money) {
        this.money = money;
    }

    public int purchaseCount() {
        return money/1000;
    }
}
