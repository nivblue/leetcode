package leetcode.a;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RateLimiter {
    private static final int REQUESTS_LIMIT = 5;
    private static final int MINUTE_IN_SECONDS = 5;

    private static final Map<String, Queue<Long>> requestMap = new HashMap<>();

    private static void test() throws InterruptedException {
        String id = "abc21";
        System.out.println(shouldAccept(id));
        System.out.println(shouldAccept(id));
        System.out.println(shouldAccept(id));
        System.out.println(shouldAccept(id));
        System.out.println(shouldAccept(id));
        // false
        System.out.println(shouldAccept(id));
        Thread.sleep(5000);
        // true
        System.out.println(shouldAccept(id));
    }

    /**
     * You need to implement a rate limiter for an API that restricts the number of requests a user can make within a certain time window (e.g., 100 requests per minute
     *
     *  1 1 1 1 11 1 1 1 1 1 1 40 seconds (- minute), 1 - false
     *
     *  1) id exist
     *     a) new list
     *  2) list -> 1 minute -> new list
     *  3) < 100 -> add to list
     *   a) false
     * */
    private static boolean shouldAccept(String id) {
        if (requestMap.containsKey(id)) {
            Queue<Long> queue = requestMap.get(id);

            return isPassLimit(queue);
        }

        Queue<Long> newRequestQueue = new PriorityQueue<>();
        newRequestQueue.add(Instant.now().getEpochSecond());
        requestMap.put(id, newRequestQueue);

        return true;
    }

    private static boolean isPassLimit(Queue<Long> queue) {
        long currentTimestamp = Instant.now().getEpochSecond();

        while (!queue.isEmpty() &&
                (currentTimestamp - queue.peek()) >= MINUTE_IN_SECONDS) {
            queue.poll();
        }

        if (queue.size() >= REQUESTS_LIMIT) {
            return false;
        }

        queue.add(currentTimestamp);

        return true;
    }
}
