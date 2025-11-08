package leetcode.LeetInfra;

import leetcode.LeetInfra.annotations.AnnotationScanner;
import leetcode.LeetInfra.annotations.LeetCodeToRun;
import leetcode.LeetInfra.logger.LeetLogger;
import leetcode.LeetInfra.times.RunMeasureTool;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static leetcode.LeetInfra.logger.LeetLogger.*;

public class LeetRunner {
    private static final String DEFAULT_PATH = "leetcode.LeetInfra";
    public static final Class<LeetCodeToRun> LEET_CODE_TO_RUN_CLASS = LeetCodeToRun.class;
    public static final Class<LeetClass> PARENT_TYPE = LeetClass.class;

    private final String leetClassesDir;
    private final Set<Class<? extends LeetClass>> classList;

    public LeetRunner() {
        this(DEFAULT_PATH);
    }

    public LeetRunner(String leetClassesDir) {
        this.leetClassesDir = leetClassesDir;
        this.classList = new HashSet<>();
    }

    public void runLeets() throws LeetRunFailedException {
        List<Class<? extends LeetClass>> tasks = this.findClasses();

        RunMeasureTool runMeasureTool = new RunMeasureTool();

        if (tasks.isEmpty()) {
            warn("WARNING: No leetcode classes found...");
            return;
        }

        for (Class<? extends LeetClass> clazz : tasks) {
            runMeasureTool.startTimer();
            this.runOneClass(clazz);
            double duration = runMeasureTool.endTimer();
            String durationString = String.format("%.3f ms", duration);
            success("Class " + clazz.getSimpleName() + " passed successfully [" + durationString + "]", LeetLogger.getBOLD_TEXT());
        }
    }

    public void addClassManually(Class<? extends LeetClass> classToAdd) {
        this.addClassManually(classToAdd, false);
    }

    public void addClassManually(Class<? extends LeetClass> classToAdd, boolean forceEnable) {
        Optional.ofNullable(classToAdd)
                .filter(LeetClass.class::isAssignableFrom)
                .filter(cls -> cls.isAnnotationPresent(LeetCodeToRun.class))
                .filter(cls -> cls.getAnnotation(LeetCodeToRun.class).enabled() || forceEnable)
                .ifPresentOrElse(this.classList::add, () -> {
                    warn("Not able to add " + classToAdd.getSimpleName() + " to class list, possible problems:");
                    warn("1) The class doesn't extand the LeetClass<>");
                    warn("2) The class is not annotated by the @LeetCodeToRun annotation");
                    warn("2) The class is annotated, but set to (enabled=false)");
                });
    }

    private List<Class<? extends LeetClass>> findClasses() {
        List<Class<? extends LeetClass>> classes =
                AnnotationScanner.findAnnotated(leetClassesDir, LEET_CODE_TO_RUN_CLASS, PARENT_TYPE);

        if (!this.classList.isEmpty()) {
            classes.addAll(this.classList);
        }

        return classes;
    }

    private void runOneClass(Class<?> clazz) throws LeetRunFailedException {
        // 1) Skip classes that aren't LeetClass
        if (!LeetClass.class.isAssignableFrom(clazz)) {
            // treat as "not runnable" rather than a failure
            logMessage("Skipping " + clazz.getName() + " (not a LeetClass)");
            logMessage(
                    String.format("*Note: If want to run this leet -> extend the %s.LeetClass<TOUPUT> class", DEFAULT_PATH));
            return;
        }

        try {
            Class<? extends LeetClass<?>> typed = (Class<? extends LeetClass<?>>) clazz;

            // 2) Reflective plumbing
            LeetClass<?> instance = typed.getDeclaredConstructor().newInstance();

            logMessage("Running class tests : " + clazz.getSimpleName());
            // 3) Let testRunner exceptions bubble up

            instance.testRunner();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // Pure reflection issues (missing ctor, abstract class, inaccessible, etc.)
            error("Reflection error for " + clazz.getName() + ": " + e);
            // Optionally rethrow as a runtime or logged-and-continue
            warn("!!This is a safe runner, so ignoring those exception, but make sure to fix them!!");
        } catch (LeetRunFailedException e) {
            warn("Failed on class :" + clazz.getSimpleName());

            throw e;
        }
    }
}
