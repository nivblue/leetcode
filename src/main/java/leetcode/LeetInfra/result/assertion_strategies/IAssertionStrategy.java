package leetcode.LeetInfra.result.assertion_strategies;

import java.util.function.BiPredicate;

public interface IAssertionStrategy<TOUTPUT> {
    TOUTPUT castToAssertionType(Object obj);

    boolean isAssertionType(Object type);

    boolean assertResult(TOUTPUT actual, TOUTPUT expected, BiPredicate<Object, Object> assertionCallback);
}
