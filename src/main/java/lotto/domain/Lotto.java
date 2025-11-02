package lotto.domain;

import static lotto.domain.exception.ErrorMessage.DUPLICATE_LOTTO_NUMBER;
import static lotto.domain.exception.ErrorMessage.INVALID_NUMBER_COUNT;
import static lotto.domain.exception.ErrorMessage.INVALID_NUMBER_RANGE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_DEFAULT_SIZE = 6;
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortAscending(numbers);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateDuplicateNumber(numbers);
        validateNumberRange(numbers);
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        Set<Integer> validateSet = new HashSet<>(numbers);
        if(validateSet.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.toString());
        }
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_DEFAULT_SIZE) {
            throw new IllegalArgumentException(INVALID_NUMBER_COUNT.toString());
        }
    }

    private List<Integer> sortAscending(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    private void validateNumberRange(List<Integer> numbers) {
        numbers.forEach(number -> {
            if (number < MIN_RANGE || number > MAX_RANGE) {
                throw new IllegalArgumentException(INVALID_NUMBER_RANGE.toString());
            }
        });
    }
}
