package lotto.domain;

import static lotto.exception.ErrorMessage.INVALID_MAX_MONEY;
import static lotto.exception.ErrorMessage.INVALID_MIN_MONEY;
import static lotto.exception.ErrorMessage.INVALID_MONEY_UNIT;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_AMOUNT = 100000;
    private static final int PERCENT_CONVERSION = 100;

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public void validate(int amount) {
        validateMinAmount(amount);
        validateMaxAmount(amount);
        validateUnit(amount);
    }

    private void validateMinAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(INVALID_MIN_MONEY.toString());
        }
    }

    private void validateMaxAmount(int amount) {
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(INVALID_MAX_MONEY.toString());
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(INVALID_MONEY_UNIT.toString());
        }
    }

    public double calculateProfitRate(long totalPrize) {
        return ((double) totalPrize / amount) * PERCENT_CONVERSION;
    }
}
