package leetcode.LeetInfra.logger;

public class LeetLogger {
    private static final String WARNING_COLOR = "\u001B[33m";
    private static final String NORMAL_COLOR = "\u001B[0m";

    public static void warn(String message) {
        System.err.println(WARNING_COLOR + message + NORMAL_COLOR);
    }

    public static void error(String message) {
        System.err.println(message);
    }
}
