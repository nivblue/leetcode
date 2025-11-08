package leetcode.LeetInfra.result;

import leetcode.LeetInfra.result.assertion_strategies.*;

import java.lang.reflect.Array;
import java.util.*;

import static leetcode.LeetInfra.logger.LeetLogger.error;

public class LeetAssertion {
    private final List<IAssertionStrategy> assertionStrategies = List.of(
            new NullAssertion(),
            new MapAssertion(),
            new ListAssertion(),
            new ArrayAssertion(),
            new PrimitiveAssertion()
    );

    public boolean assertResult(Object actual, Object expected) {
        for (IAssertionStrategy assertionStrategy : this.assertionStrategies) {
            if (assertionStrategy.isAssertionType(expected)) {
                if (!assertionStrategy.isAssertionType(actual)) {
                    String actualType = getObjectType(actual);
                    String expectedType = getObjectType(expected);

                    error("Not the same type!");
                    error("actual : " + actualType);
                    error("expected : " + expectedType);
                    return false;
                }

                return assertionStrategy.assertResult(
                        assertionStrategy.castToAssertionType(actual),
                        assertionStrategy.castToAssertionType(expected),
                        this::assertResult);
            }
        }

        String actualType = getObjectType(actual);
        String expectedType = getObjectType(expected);

        String basicErrorMessage = "Actual [" + actualType + "] and expected [" + expectedType + "]";

        if (!actualType.equals(expectedType)) {
            error(basicErrorMessage + "Are not from the same type!");
            return false;
        }

        error(basicErrorMessage + "Assertion type not supported!");

        return false;
    }

    private String getObjectType(Object obj) {
        return Optional.ofNullable(obj)
                .map(Object::getClass)
                .map(Class::getSimpleName)
                .orElseGet(() -> "null");
    }
}
