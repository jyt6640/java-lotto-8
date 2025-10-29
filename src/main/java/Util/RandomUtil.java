package Util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomUtil {
    public static List<Integer> getLottoNumber(int count) {
        return Randoms.pickUniqueNumbersInRange(1,45, count);
    }
}
