package lotto.domain;

public class Money {

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int getCount() {
        return money / 1000;
    }

    public int getMoney() {
        return money;
    }
}
