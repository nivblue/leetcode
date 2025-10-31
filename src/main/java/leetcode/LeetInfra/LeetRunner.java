package leetcode.LeetInfra;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class LeetRunner {
    private static final String DEFAULT_PATH = "leetcode.LeetInfra";
    public static final Class<LeetCodeToRun> LEET_CODE_TO_RUN_CLASS = LeetCodeToRun.class;
    public static final Class<LeetClass> PARENT_TYPE = LeetClass.class;
    private static final String WARNING_COLOR = "\u001B[33m";
    private static final String NORMAL_COLOR = "\u001B[0m";

    private final String leetClassesDir;

    public LeetRunner() {
        this(DEFAULT_PATH);
    }

    public LeetRunner(String leetClassesDir) {
        this.leetClassesDir = leetClassesDir;
    }

    void warn(String message) {
        System.err.println(WARNING_COLOR + message + NORMAL_COLOR);
    }

    void error(String message) {
        System.err.println(message);
    }

    void runOne(Class<?> clazz) throws LeetRunFailedException {
        // 1) Skip classes that aren't LeetClass
        if (!LeetClass.class.isAssignableFrom(clazz)) {
            // treat as "not runnable" rather than a failure
            System.out.println("Skipping " + clazz.getName() + " (not a LeetClass)");
            System.out.println(
                    String.format("*Note: If want to run this leet -> extend the %s.LeetClass<TOUPUT> class", DEFAULT_PATH));
            return;
        }

        try {
            Class<? extends LeetClass<?>> typed = (Class<? extends LeetClass<?>>) clazz;

            // 2) Reflective plumbing
            LeetClass<?> instance = typed.getDeclaredConstructor().newInstance();

            System.out.println("Running class tests : " + clazz.getSimpleName());
            // 3) Let testRunner exceptions bubble up
            instance.testRunner();

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // Pure reflection issues (missing ctor, abstract class, inaccessible, etc.)
            error("Reflection error for " + clazz.getName() + ": " + e);
            // Optionally rethrow as a runtime or logged-and-continue
            warn("!!This is a safe runner, so ignoring those exception, but make sure to fix them!!");
        }
    }

    public void runLeets() throws LeetRunFailedException {
        List<Class<? extends LeetClass>> tasks =
                AnnotationScanner.findAnnotated(leetClassesDir, LEET_CODE_TO_RUN_CLASS, PARENT_TYPE);

        if (tasks.isEmpty()) {
            warn("WARNING: No leetcode classes found...");
            return;
        }

        for (Class<? extends LeetClass> clazz : tasks) {
            this.runOne(clazz);
        }
    }
}
