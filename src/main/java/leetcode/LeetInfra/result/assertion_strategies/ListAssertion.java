package leetcode.LeetInfra.result.assertion_strategies;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public class ListAssertion implements IAssertionStrategy<List<?>> {
    @Override
    public List<?> castToAssertionType(Object obj) {
        return ((List<?>) obj);
    }

    @Override
    public boolean isAssertionType(Object type) {
        return Objects.nonNull(type) && type instanceof List;
    }

    @Override
    public boolean assertResult(List<?> actual, List<?> expected, BiPredicate<Object, Object> assertionCallback) {
//        List<?> actualList = ((List<?>) actual);
//        List<?> expectedList = ((List<?>) expected);

        int aLen = actual.size();
        int eLen = expected.size();

        if (aLen != eLen) {
            System.err.println("Lists not in the same length!");
            System.err.println("actual length : " + aLen);
            System.err.println("expected length : " + eLen);
            return false;
        }

        for (int i = 0; i < aLen; i++) {
            System.out.println("Using callback on List for " + actual.get(i) + ", " + expected.get(i));
            if (!assertionCallback.test(actual.get(i), expected.get(i))) return false;
        }

        return true;
    }
}
