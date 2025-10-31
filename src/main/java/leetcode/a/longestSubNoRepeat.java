package leetcode.a;

import java.util.HashSet;

public class longestSubNoRepeat {
    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        char[] chars = s.toCharArray();

        int start = 0;
        int end = 1;
        int longest = 0;
        HashSet<Character> set = new HashSet<>();
        set.add(chars[0]);

        while (end < chars.length) {
            char currentChar = chars[end];
            if (set.contains(currentChar)) {
                int newLong = set.size();
                if (newLong > longest) {
                    longest = newLong;
                }

                set.remove(chars[start]);
                start++;
            } else {
                set.add(currentChar);

                end++;
            }
        }

        int newLong = set.size();
        if (newLong > longest) {
            longest = newLong;
        }

        return longest;
    }
}
