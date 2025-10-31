package leetcode.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupAnagrams {
    /**
     *
     * fun groupAnagrams(words: Array<String>): List<List<String>>
     *
     * Your function should take an array of strings and group the anagrams together. Anagrams are words that have the same characters in the same quantity, just in a different order. You can return the groups in any order."
     * "If the input is ["eat", "tea", "tan", "ate", "nat", "bat"], a correct output would be:"
     * [
     *   ["eat", "tea", "ate"],
     *   ["tan", "nat"],
     *   ["bat"]
     * ]
     * */
    private static List<List<String>> groupAnagrams(List<String> words) {
        Map<Integer, ArrayList<String>> anagramsMap = new HashMap<>();
        // n - words
        for (String word : words) {
            // m - word letters
            int mapHashCode = keyAsMap(word);

            if (anagramsMap.containsKey(mapHashCode)) {
                anagramsMap.get(mapHashCode).add(word);
            } else {
                ArrayList<String> newArray = new ArrayList<>();
                newArray.add(word);
                anagramsMap.put(mapHashCode, newArray);
            }
        }

        // O(n*m)

        return anagramsMap.values().stream().collect(Collectors.toList());
    }

    private static int keyAsMap(String word) {
        Map<Character,  Integer> counterMap = new HashMap<>();

        for (char c : word.toCharArray()) {
            int count = counterMap.getOrDefault(c, 0);

            counterMap.put(c, ++count);
        }

        return counterMap.hashCode();
    }

    private static String sortedString(String word) {
        return Stream.of(word.split(""))
                .sorted()
                .collect(Collectors.joining());
    }
}
