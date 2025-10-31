package leetcode.old;

import java.util.HashMap;

public class OldNumMatchingSubseq {
    public int numMatchingSubseq(String s, String[] words) {
        HashMap<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            int count = wordMap.getOrDefault(word, 0) + 1;
            wordMap.put(word, count);
        }

        int count = 0;
        char[] sChars = s.toCharArray();
        for (String word : wordMap.keySet()) {
            if (isSuseq(sChars, word)) {
                count += wordMap.get(word);
            }
        }

        return count;
    }

    public boolean isSuseq(char[] sChars, String word) {
        int i = 0, j = 0;
        char[] wordChars = word.toCharArray();
        while (i < sChars.length) {
            if (sChars[i] == wordChars[j]) {
                if (j == wordChars.length - 1) {
                    return true;
                }

                j++;
            }

            i++;
        }

        return false;
    }
}
