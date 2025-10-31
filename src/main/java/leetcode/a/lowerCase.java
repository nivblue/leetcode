package leetcode.a;

public class lowerCase {
    public static String toLowerCase(String s) {
        final int DIFF = 32; // A-a
        final int MIN = 65; // A
        final int MAX = 90; // Z

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (MIN <= c && c <= MAX) {
                char lowerCase = (char)(c + DIFF);
                stringBuilder.append(lowerCase);
            } else {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }
}
