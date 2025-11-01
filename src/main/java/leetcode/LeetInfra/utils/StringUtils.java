package leetcode.LeetInfra.utils;

import java.util.Map;

import static leetcode.LeetInfra.logger.LeetLogger.error;

public class StringUtils {
    private static final int DEFAULT_REPEAT = 1;

    private static final char OPEN_SQUARE_BRACKETS = '[';
    private static final char CLOSE_SQUARE_BRACKETS = ']';
    private static final char OPEN_CURLEY_BRACKETS = '{';
    private static final char CLOSE_CURLEY_BRACKETS = '}';
    private static final char EOL_JAVA_CHAR = ';';

    private static final String ARRAY_BRACKETS_STRING = "[]";
    private static final String DEFAULT_ARRAY_NAME = "array";

    private static final Map<String, Class<?>> PRIMITIVES = Map.ofEntries(
            Map.entry("boolean", boolean.class),
            Map.entry("byte", byte.class),
            Map.entry("char", char.class),
            Map.entry("short", short.class),
            Map.entry("int", int.class),
            Map.entry("long", long.class),
            Map.entry("float", float.class),
            Map.entry("double", double.class),
            Map.entry("void", void.class)
    );

    public static String javaArrayFormat(String arrayString) {
        return arrayString
                .replace(OPEN_SQUARE_BRACKETS, OPEN_CURLEY_BRACKETS)
                .replace(CLOSE_SQUARE_BRACKETS, CLOSE_CURLEY_BRACKETS);
    }

    public static String toJavaArray(String arrayDataString, String stringArrayType) throws ClassNotFoundException {
        return toJavaArray(arrayDataString, stringArrayType, DEFAULT_REPEAT, DEFAULT_ARRAY_NAME);
    }

    public static String toJavaArray(String arrayDataString, String stringArrayType, String arrayName) throws ClassNotFoundException {
        return toJavaArray(arrayDataString, stringArrayType, DEFAULT_REPEAT, arrayName);
    }

    public static String toJavaArray(String arrayDataString, String stringArrayType, int arrayDimensionsRepeat) throws ClassNotFoundException {
        return toJavaArray(arrayDataString, stringArrayType, arrayDimensionsRepeat, DEFAULT_ARRAY_NAME);
    }

    public static String toJavaArray(String arrayDataString, String stringArrayType, int arrayDimensionsRepeat, String arrayName) throws ClassNotFoundException {
        String brackets = ARRAY_BRACKETS_STRING.repeat(arrayDimensionsRepeat);
        String javaFormatDataString = javaArrayFormat(arrayDataString);
        StringBuilder solution = new StringBuilder();

        try {
            if (!PRIMITIVES.containsKey(stringArrayType)) {
                Class.forName(stringArrayType);
            }

            solution.append(stringArrayType);
            solution.append(brackets);
            solution.append(" ");
            solution.append(arrayName);
            solution.append(" = new ");
            solution.append(stringArrayType);
        } catch(Exception e) {
            error("Exception happened when trying to check your type : " + stringArrayType + ", e : " + e);
        }

        solution.append(brackets);
        solution.append(javaFormatDataString);
        solution.append(EOL_JAVA_CHAR);

        return solution.toString();
    }
}
