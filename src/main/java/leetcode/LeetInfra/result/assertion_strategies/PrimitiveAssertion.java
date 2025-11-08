package leetcode.LeetInfra.result.assertion_strategies;

import java.util.Objects;
import java.util.function.BiPredicate;

import static leetcode.LeetInfra.logger.LeetLogger.error;

public class PrimitiveAssertion implements IAssertionStrategy {
    @Override
    public Object castToAssertionType(Object obj) {
        return obj;
    }

    @Override
    public boolean isAssertionType(Object type) {
        return Objects.nonNull(type);
    }

    @Override
    public boolean assertResult(Object actual, Object expected, BiPredicate assertionCallback) {
        if (!actual.getClass().getSimpleName().equals(expected.getClass().getSimpleName())) {
            error("Actual [" + actual.getClass().getSimpleName() + "]" +
                    " and expected [" + expected.getClass().getSimpleName() + "]" +
                    "Are not from the same type!");
            return false;
        }

        return actual.equals(expected);
    }
}
