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

    public LeetRun(String runName, Supplier<TOUTPUT> leetRunSupplier, TOUTPUT expected) {
        this.runName = runName;
        this.leetRunSupplier = leetRunSupplier;
        this.expected = expected;
    }

    public LeetRun(Supplier<TOUTPUT> leetRunSupplier, TOUTPUT expected) {
        this(null, leetRunSupplier, expected);
    }

    public Optional<String> getRunName() {
        return Optional.ofNullable(runName);
    }
}
