package leetcode.LeetInfra;

import lombok.Getter;

import java.util.Optional;
import java.util.function.Supplier;

@Getter
public class LeetRun<TOUTPUT> {
    private static final String DEFAULT_TITLE = "";

    private String runName;
    private Supplier<TOUTPUT> leetRunSupplier;
    private TOUTPUT expected;
    private boolean isRun;

    public LeetRun(String runName, Supplier<TOUTPUT> leetRunSupplier, TOUTPUT expected, boolean isRun) {
        this.runName = runName;
        this.leetRunSupplier = leetRunSupplier;
        this.expected = expected;
        this.isRun = isRun;
    }

    public LeetRun(String runName, Supplier<TOUTPUT> leetRunSupplier, TOUTPUT expected) {
        this(runName, leetRunSupplier, expected, true);
    }

    public LeetRun(Supplier<TOUTPUT> leetRunSupplier, TOUTPUT expected) {
        this(null, leetRunSupplier, expected);
    }

    public Optional<String> getRunName() {
        return Optional.ofNullable(runName);
    }
}
