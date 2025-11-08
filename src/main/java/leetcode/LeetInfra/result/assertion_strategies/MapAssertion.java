package leetcode.LeetInfra.result.assertion_strategies;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;

import static leetcode.LeetInfra.logger.LeetLogger.error;

public class MapAssertion implements IAssertionStrategy<Map<?,?>> {
    @Override
    public Map<?,?> castToAssertionType(Object obj) {
        return ((Map<?, ?>) obj);
    }

    @Override
    public boolean isAssertionType(Object type) {
        return Objects.nonNull(type) && type instanceof Map;
    }

    @Override
    public boolean assertResult(Map<?,?> actual, Map<?,?> expected, BiPredicate<Object, Object> assertionCallback) {
        Set<? extends Map.Entry<?, ?>> actualEntries = actual.entrySet();
        int aLen = actualEntries.size();
        int eLen = expected.size();
        if (eLen != actualEntries.size()) {
            error("Maps size not the same!");
            error("actual length : " + aLen);
            error("expected length : " + eLen);

            return false;
        }

        for (Map.Entry<?, ?> entry : actualEntries) {
            Object key = entry.getKey();
            Object actualValue = entry.getValue();

            if (!expected.containsKey(key)) {
                System.err.println("expected is missing following key : " + key + " [value=" + actualValue + "]");
                return false;
            }

            Object expectedValue = expected.get(key);

            if (!assertionCallback.test(actualValue, expectedValue)) return false;
        }

        return true;
    }
}
