package lotto.view;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printError(IllegalAccessError e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }
}
