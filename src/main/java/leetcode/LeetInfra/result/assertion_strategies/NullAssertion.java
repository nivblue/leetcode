package leetcode.LeetInfra.result.assertion_strategies;

import java.util.Objects;
import java.util.function.BiPredicate;

public class NullAssertion implements IAssertionStrategy {
    @Override
    public Object castToAssertionType(Object obj) {
        return obj;
    }

    @Override
    public boolean isAssertionType(Object type) {
        return Objects.isNull(type);
    }

    @Override
    public boolean assertResult(Object actual, Object expected, BiPredicate assertionCallback) {
        return Objects.isNull(actual) && Objects.isNull(expected);
    }
}
