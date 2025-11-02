package lotto.domain.exception;

public enum ErrorMessage {
    INVALID_INPUT_NOT_BLANK("입력값은 공백일 수 없습니다."),
    INVALID_INPUT_ONLY_NUMBER("숫자만 입력 가능합니다."),
    INVALID_NUMBER_RANGE("1~45 범위 값만 허용됩니다."),
    INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_LOTTOS_SIZE("로또는 하나 이상 생성해야 합니다."),
    INVALID_MIN_MONEY("최소 금액은 1,000원 이상이어야 합니다."),
    INVALID_MAX_MONEY("한 회차당 구매 가능 금액은 10만원입니다."),
    INVALID_MONEY_UNIT("금액은 1,000원 단위이어야 합니다."),
    INVALID_INT_RANGE("입력값은 int 범위를 넘어갈 수 없습니다."),
    INVALID_WINNING_NUMBER_FORMAT("당첨 번호는 쉼표(,)로 구분된 숫자 형식이어야 합니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    DUPLICATE_LOTTO_NUMBER("중복된 숫자는 입력할 수 없습니다.");

    private final static String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE_PREFIX + message;
    }

}
