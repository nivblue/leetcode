package leetcode.LeetInfra.result;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static leetcode.LeetInfra.logger.LeetLogger.error;

public class LeetAssertion {
    private final Map<Predicate<Object>, BiPredicate<Object, Object>> assertionsMap = Map.ofEntries(
            Map.entry(this::isArray, this::assertArrays),
            Map.entry(this::isMap,   (a, b) -> assertMaps((Map<?, ?>) a, (Map<?, ?>) b)),
            Map.entry(this::isList,  (a, b) -> assertLists((List<?>) a, (List<?>) b))
    );

    public boolean assertResult(Object actual, Object expected) {
        if (Objects.isNull(expected)) {
            System.out.println("Null assertions");
            return Objects.isNull(actual);
        }

        for (Map.Entry<Predicate<Object>, BiPredicate<Object, Object>> entry : this.assertionsMap.entrySet()) {
            Predicate<Object> assertionType = entry.getKey();

            if (assertionType.test(expected)) {
                System.out.println("Asserting " + assertionType.toString());

                if (!assertionType.test(actual)) {
                    error("Not the same type!");
                    error("actual : " + actual.getClass().getSimpleName());
                    error("expected : " + expected.getClass().getSimpleName());
                    return false;
                }

                return entry.getValue().test(actual, expected);
            }
        }

        System.out.println("class : " + actual.getClass().getSimpleName() + ", " + expected.getClass().getSimpleName());
        return actual.equals(expected);
    }

    private boolean assertLists(List<?> actual, List<?> expected) {
        int aLen = actual.size();
        int eLen = expected.size();

        if (aLen != eLen) {
            System.err.println("Lists not in the same length!");
            System.err.println("actual length : " + aLen);
            System.err.println("expected length : " + eLen);
            return false;
        }

        for (int i = 0; i < aLen; i++) {
            if (!assertResult(actual.get(i), expected.get(i))) return false;
        }

        return true;
    }

    private boolean assertMaps(Map<?, ?> actual, Map<?, ?> expected) {
        for (Map.Entry<?, ?> entry : actual.entrySet()) {
            Object key = entry.getKey();
            Object actualValue = entry.getValue();

            if (!expected.containsKey(key)) {
                System.err.println("expected is missing following key : " + key + " [value=" + actualValue + "]");
                return false;
            }

            Object expectedValue = expected.get(key);

            if (!this.assertResult(actualValue, expectedValue)) return false;
        }

        return true;
    }

    private boolean assertArrays(Object actual, Object expected) {
        int aLen = Array.getLength(actual);
        int eLen = Array.getLength(expected);

        if (aLen != eLen) {
            System.err.println("Arrays not in the same length!");
            System.err.println("actual length : " + aLen);
            System.err.println("expected length : " + eLen);
            return false;
        }

        for (int i = 0; i < aLen; i++) {
            Object aElem = Array.get(actual, i);
            Object eElem = Array.get(expected, i);

            if (!assertResult(aElem, eElem)) return false;
        }

        return true;
    }

    private boolean isArray(Object type) {
        return Objects.nonNull(type) && type.getClass().isArray();
    }

    private boolean isMap(Object type) {
        return Objects.nonNull(type) && type instanceof Map;
    }

    private boolean isList(Object type) {
        return Objects.nonNull(type) && type instanceof List;
    }
}
