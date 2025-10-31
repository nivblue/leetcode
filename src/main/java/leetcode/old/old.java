package leetcode.old;

import java.util.HashMap;
import java.util.LinkedList;

public class old {
    public static int numMatchingSubseq(String s, String[] words) {
        HashMap<Character, LinkedList<Integer>> charToIndexes = new HashMap<>();
        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i++) {
            LinkedList<Integer> indexes = charToIndexes.getOrDefault(sChars[i], new LinkedList<>());
            indexes.add(i);
            charToIndexes.put(sChars[i], indexes);
        }

        int count = 0;
        for (String word : words) {
            if (isSubseq(charToIndexes, word)) {
                count++;
            }
        }

        return count;
    }

    private static HashMap<Character, LinkedList<Integer>> deepCopyMap(HashMap<Character, LinkedList<Integer>> charToIndexes) {
        HashMap<Character, LinkedList<Integer>> copy = new HashMap<>();
        charToIndexes.forEach((key, value) -> copy.put(key,  new LinkedList<>(value)));

        return copy;
    }

    private static boolean isSubseq(HashMap<Character, LinkedList<Integer>> charToIndexes, String word) {
        HashMap<Character, LinkedList<Integer>> charToIndexCopy = deepCopyMap(charToIndexes);

        int i = 0;
        for (char currentChar : word.toCharArray()) {
            if (!charToIndexCopy.containsKey(currentChar)) {
                return false;
            }

            LinkedList<Integer> linkedList = charToIndexCopy.get(currentChar);
            if (linkedList.isEmpty()) {
                return false;
            }

            int newIndex = linkedList.pop();
            if (i > newIndex) {
                return false;
            }

            i = newIndex;
        }

        return true;
    }
}
