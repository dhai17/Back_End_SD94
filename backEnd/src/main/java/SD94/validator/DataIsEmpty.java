package SD94.validator;

import java.util.Arrays;

public class DataIsEmpty {
    public static <T> boolean isEmpty(T t) {
        if (t == null) {
            return true;
        }

        if (t instanceof String) {
            return ((String) t).isEmpty();
        }

        if (t instanceof Integer) {
            return ((Integer) t) == 0;
        }

        if (t.getClass().isArray()) {
            if (t instanceof Object[]) {
                return Arrays.stream((Object[]) t).allMatch(element -> isEmpty(element));
            } else if (t instanceof long[]) {
                return Arrays.stream((long[]) t).allMatch(element -> element == 0);
            } else {
                return false;
            }
        }

        return false;
    }
}
