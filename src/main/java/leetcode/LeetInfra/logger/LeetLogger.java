package leetcode.LeetInfra.logger;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.err;
import static java.lang.System.out;

public class LeetLogger {
    @Getter
    private static final String BOLD_TEXT    = "\u001B[1m";

    private static final String WARNING_YELLOW_COLOR = "\u001B[33m";
    private static final String SUCCESS_GREEN_COLOR = "\u001B[32m";
    private static final String NORMAL_COLOR = "\u001B[0m";



    public static void warn(String message) {
        err.println(WARNING_YELLOW_COLOR + message + NORMAL_COLOR);
    }

    public static void error(String message) {
        err.println(message);
    }

    public static void success(String message, String... colorSettings) {
        if (Objects.nonNull(colorSettings) && colorSettings.length > 0) {
            colorSettings = Arrays.copyOf(colorSettings, colorSettings.length + 1);
            colorSettings[colorSettings.length - 1] = SUCCESS_GREEN_COLOR;
        } else {
            colorSettings = new String[]{SUCCESS_GREEN_COLOR};
        }

        logMessage(message, colorSettings);
    }

    public static void logMessage(String message, String... colorSettings) {
        StringBuilder color = new StringBuilder();
        for (String c : colorSettings) {
            color.append(c);
        }

        out.println(color + message + NORMAL_COLOR);
    }
}