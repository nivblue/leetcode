package leetcode.a;

import java.util.HashSet;
import java.util.Set;

public class ExcercisesDone {
    public static void testCaseLengthOfLongestSubstring() {
        runTest("abcabcbb", 3);
        runTest("bbbbb",    1);
        runTest("pwwkew",   3);
        runTest("",       0);
        runTest("anviaj",       5);
    }

    public static void runTest(String s, int expected) {
        int result = lengthOfLongestSubstring(s);
        if (result == expected) {
            System.out.printf("PASS: \"%s\" → %d%n", s, result);
        } else {
            System.out.printf("FAIL: \"%s\" → got %d but expected %d%n",
                    s, result, expected);
        }
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> usedChars = new HashSet<>();
        char[] asCharArray = s.toCharArray();
        int biggest = 0;

        for (int i = 0; i < asCharArray.length; i++) {
            usedChars.add(asCharArray[i]);
            for (int j = i + 1;
                 j < asCharArray.length && !usedChars.contains(asCharArray[j]);
                 j++) {
                usedChars.add(asCharArray[j]);
            }

            if (usedChars.size() > biggest) {
                biggest = usedChars.size();
            }
            usedChars.clear();
        }

        return biggest;
    }

    public static int lengthOfLongestSubstringSolution(String s) {
        int n = s.length();
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (!charSet.contains(s.charAt(right))) {
                charSet.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                while (charSet.contains(s.charAt(right))) {
                    charSet.remove(s.charAt(left));
                    left++;
                }
                charSet.add(s.charAt(right));
            }
        }

        return maxLength;
    }
}
