package leetcode.LeetInfra.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LeetCodeToRun {
    /**
     * Whether this class should be run.
     * Defaults to true (enabled).
     */
    boolean enabled() default true;
}