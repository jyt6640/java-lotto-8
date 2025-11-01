package lotto.domain;

public class Money {
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / 1000;
    }

    public void validate(int amount) {
        validateMinAmount(amount);
        validateMaxAmount(amount);
        validateUnit(amount);
    }

    private void validateMinAmount(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("[ERROR] 최소 금액은 1,000원 이상이어야 합니다.");
        }
    }

    private void validateMaxAmount(int amount) {
        if (amount > 100000) {
            throw new IllegalArgumentException("[ERROR] 한 회차당 구매 가능 금액은 10만원입니다.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1,000원 단위이어야 합니다.");
        }
    }
}
