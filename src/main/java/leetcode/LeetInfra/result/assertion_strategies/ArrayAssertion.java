package leetcode.LeetInfra.result.assertion_strategies;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.function.BiPredicate;

import static leetcode.LeetInfra.logger.LeetLogger.error;

public class ArrayAssertion implements IAssertionStrategy {
    @Override
    public Object castToAssertionType(Object obj) {
        return obj;
    }

    @Override
    public boolean isAssertionType(Object type) {
        return Objects.nonNull(type) && type.getClass().isArray();
    }

    @Override
    public boolean assertResult(Object actual, Object expected, BiPredicate assertionCallback) {
        int aLen = Array.getLength(actual);
        int eLen = Array.getLength(expected);

        if (aLen != eLen) {
            error("Arrays not in the same length!");
            error("actual length : " + aLen);
            error("expected length : " + eLen);

            return false;
        }

        for (int i = 0; i < aLen; i++) {
            Object aElem = Array.get(actual, i);
            Object eElem = Array.get(expected, i);

            if (!assertionCallback.test(aElem, eElem)) return false;
        }

        return true;
    }
}
