package leetcode.a;

import java.util.LinkedList;

public class removeBoxes {
    public static int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        int max = 0;
        LinkedList<Integer> newBoxes = new LinkedList<>();
        for (int i = 0; i < boxes.length; i++) {
            newBoxes.add(boxes[i]);
        }

        for(int i = 0; i < boxes.length; i++) {
            int counter = 1;
            int index = i + 1;
            newBoxes.remove(i);
            for (; index < boxes.length; index++) {
                if (boxes[index] == boxes[i]) {
                    newBoxes.remove(i);
                    counter++;
                } else {
                    break;
                }
            }

            int currentValue =
                    removeBoxes(newBoxes.stream().mapToInt(Integer::intValue).toArray());

            for (int k = i; k < index; k++) {
                newBoxes.add(k, boxes[i]);
            }

            currentValue += counter * counter;

            if (currentValue > max) {
                max = currentValue;
            }
        }

        return max;
    }
}
