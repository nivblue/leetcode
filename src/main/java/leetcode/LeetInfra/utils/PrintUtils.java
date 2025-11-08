package leetcode.LeetInfra.utils;

import java.lang.reflect.Array;

public class PrintUtils {
    public static String getArrayString(Object arr) {
        StringBuilder arrayString = new StringBuilder();
        arrayString.append("[");
        int len = Array.getLength(arr);
        for (int i = 0; i < len; i++) {
            Object o = Array.get(arr, i);
            if (o.getClass().isArray()) {
                arrayString.append(getArrayString(o));
            } else {
                arrayString.append(" " + o + " ");
            }
        }
        arrayString.append("]");

        return arrayString.toString();
    }


    public static void printArray(Object arr) {
        System.out.println(getArrayString(arr));
    }
}
