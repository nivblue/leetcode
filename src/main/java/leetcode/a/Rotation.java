package leetcode.a;

public class Rotation {
    public static void testRotation() {
//        String ring = "godding";
//        String key = "godding";

        String ring = "ababcab";
        String key = "acbaacba";
        int steps = findRotateSteps(ring, key);
        System.out.println("Minimum steps to spell the key: " + steps);
        System.out.println("Expected: 17");
    }

    public static int findRotateSteps(String ring, String key) {
        if (key.length() == 0) {
            return 0;
        }

        char firstChar = key.charAt(0);
        int indexFromStart = ring.indexOf(firstChar);
        int indexFromEnd = ring.lastIndexOf(firstChar);

        String end = ring.substring(indexFromStart);
        String start = ring.substring(0, indexFromStart);
        String startRing = end.concat(start);

        end = ring.substring(indexFromEnd);
        start = ring.substring(0, indexFromEnd);
        String endRing = end.concat(start);
        String subKey = key.substring(1);

        int startSteps = findRotateSteps(startRing, subKey);
        int endSteps = findRotateSteps(endRing, subKey);

        if (startSteps <= endSteps) {
            return startSteps + indexFromStart + 1;
        } else {
            return endSteps + (ring.length() - indexFromEnd) + 1;
        }
    }

    public int currentSolution(String ring, String key) {
        int steps = 0;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);

            int indexFromStart = ring.indexOf(c);
            int indexFromEnd = ring.lastIndexOf(c);
            int cutIndex = indexFromStart;

            if (indexFromStart == 0) {
                steps++;
                continue;
            }

            if (indexFromStart == ring.length() - indexFromEnd) {
                String end = ring.substring(indexFromStart);
                String start = ring.substring(0, indexFromStart);
                String startRing = end.concat(start);

                end = ring.substring(indexFromEnd);
                start = ring.substring(0, indexFromEnd);
                String endRing = end.concat(start);
                String subKey = key.substring(i+1);

                System.out.println("current ring : " + ring);
                System.out.println("Current steps : " + steps);
                System.out.println("startRing : " + startRing + ", endRing : " + endRing);
                System.out.println("index : " + i + ", current char : " + c + ", subkey : " + subKey);

                int startSteps = findRotateSteps(startRing, subKey);

                int endSteps = findRotateSteps(endRing, subKey);

                System.out.println("current steps before plus : " + steps);
                System.out.println("startsteps : " + startSteps + ", endStesp : " + endSteps);
                System.out.println("indexFromStart : " + indexFromStart);

                return steps + Math.min(startSteps, endSteps) + indexFromStart + 1;
            }

            if (indexFromStart < ring.length() - indexFromEnd) {
                steps += indexFromStart + 1;
            } else {
                steps += (ring.length() - indexFromEnd) + 1;
                cutIndex = indexFromEnd;
            }

            String end = ring.substring(cutIndex);
            String start = ring.substring(0, cutIndex);
            ring = end.concat(start);
        }

        return steps;
    }
}
