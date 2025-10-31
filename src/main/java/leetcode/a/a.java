package leetcode.a;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a {
    public static int maximumSafenessFactor(List<List<Integer>> grid) {
        int size = grid.size();

        if (grid.get(0).get(0) == 1 ||
                grid.get(size -1).get(size - 1) == 1) {
            return 0;
        }

        Set<int[]> allThiefs = new HashSet<>();

        // O(n^2)
        for (int i = 0; i < grid.size(); i++) {
            List<Integer> cells = grid.get(i);
            for (int j = 0; j < cells.size(); j++) {
                if (cells.get(j) == 1) {
                    allThiefs.add(new int[] {i, j});
                    cells.add(j, -1);
                }
            }
        }

        // O - n^2 * allThiefs ??
        for (int i = 0; i < grid.size(); i++) {
            List<Integer> cells = grid.get(i);
            for (int j = 0; j < cells.size(); j++) {
                if (cells.get(j) == 0) {
                    cells.add(j, findSmallestDistance(i, j, allThiefs));
                }
            }
        }

        Set<int[]> path = new HashSet<>();
        int xIndex = 0;
        int yIndex = 0;

        return findSafestPath(xIndex, yIndex, grid, path);
    }

    private static int findSafestPath(int xIndex, int yIndex, List<List<Integer>> grid, Set<int[]> path) {
        int size = grid.size();

        if (xIndex == size - 1 && yIndex == size - 1) {
            path.add(new int[] {xIndex, yIndex});
            return grid.get(yIndex).get(xIndex);
        }

        if (grid.get(yIndex).get(xIndex) == -1) {
            return 0;
        }

        int[] solution = new int[] {xIndex, yIndex};

        int current = 0;

        if (yIndex < size - 1 && !path.contains(new int[] {xIndex, yIndex+1})) {
            int next = findSafestPath(xIndex, yIndex + 1, grid, path);
            if (next > current) {
                solution[1] = yIndex+1;
                current = next;
            }
        }

        if (yIndex > 0 && !path.contains(new int[] {xIndex, yIndex-1})) {
            int next = findSafestPath(xIndex, yIndex - 1, grid, path);
            if (next > current) {
                solution[1] = yIndex-1;
                current = next;
            }
        }

        if (xIndex < size - 1 && !path.contains(new int[] {xIndex + 1, yIndex})) {
            int next = findSafestPath(xIndex + 1, yIndex, grid, path);
            if (next > current) {
                solution[0] = xIndex+1;
                current = next;
            }
        }

        if (xIndex > 0 && !path.contains(new int[] {xIndex - 1, yIndex})) {
            int next = findSafestPath(xIndex - 1, yIndex, grid, path);
            if (next > current) {
                solution[0] = xIndex-1;
                current = next;
            }
        }

        if (current == 0) {
            return 0;
        }

        path.add(solution);

        return current;
    }

    private static int findSmallestDistance(int i, int j, Set<int[]> allTheifs) {
        int minimum = allTheifs.size(); // find a better number
        for (int[] thiefLocation : allTheifs) {
            int distance = Math.abs(thiefLocation[0] - i) + Math.abs(thiefLocation[1] - j);

            if (distance < minimum) {
                minimum = distance;
            }
        }

        return minimum;
    }
}
