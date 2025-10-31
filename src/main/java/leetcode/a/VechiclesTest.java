package leetcode.a;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VechiclesTest {
    private static final int AMOUNT = 10000;
    private static final long MINUTE_IN_MILLIS = 60000;

    private static void test() {
        VehicleUpdate[] updates = HttpWrapper.getAllUpdates(AMOUNT);

        printNumberOfMessagesPerModel(updates);

        Map<String, Integer> stringIntegerMap = numberOfMessagesPerModel(updates);

        System.out.println(stringIntegerMap);

//        List<VehicleUpdate> vehicleUpdates = returnLatestMessages(updates);
//
//        vehicleUpdates.forEach(System.out::println);
//        System.out.println(vehicleUpdates.size());
    }

    private static Map<String, Integer> numberOfMessagesPerModel(VehicleUpdate[] vehicleUpdates) {
        // 1️⃣ Compute the cutoff timestamp for “five minutes ago”
        long fiveMinutesAgo = Instant.now().toEpochMilli() - MINUTE_IN_MILLIS * 5;

        // 2️⃣ Stream the array of updates
        return Arrays.stream(vehicleUpdates)

                // 3️⃣ Keep only updates whose timestamp is within the last 5 minutes
                .filter(update -> update.timestamp >= fiveMinutesAgo)

                // 4️⃣ Group by model, and for each model count 1 per element
                .collect(Collectors.groupingBy(
                        update -> update.model,             // key: the vehicle model
                        Collectors.summingInt(u -> 1)       // downstream: sum 1 for each update
                ));
    }

    private static void printNumberOfMessagesPerModel(VehicleUpdate[] vehicleUpdates) {
        Map<String, Integer> modelMessagesCounter = new HashMap<>();
        long currentMilliseconds = Instant.now().toEpochMilli();

        for (VehicleUpdate update : vehicleUpdates) {
            if (currentMilliseconds - update.timestamp <= (MINUTE_IN_MILLIS * 5)) {
                int modelCounter = modelMessagesCounter.getOrDefault(update.model, 0);

                modelMessagesCounter.put(update.model, ++modelCounter);
            }
        }

        System.out.println(modelMessagesCounter);
    }

    private static List<VehicleUpdate> returnLatestMessages(VehicleUpdate[] vehicleUpdates) {
        Map<String, VehicleUpdate> vechiclesMap = new HashMap<>();

        for (VehicleUpdate update : vehicleUpdates) {
            if (!vechiclesMap.containsKey(update.vin)) {
                vechiclesMap.put(update.vin, update);
            } else {
                long lastUpdateTimestamp = vechiclesMap.get(update.vin).timestamp;

                if (update.timestamp > lastUpdateTimestamp) {
                    vechiclesMap.put(update.vin, update);
                }
            }
        }

        return vechiclesMap.values().stream().collect(Collectors.toList());
    }
}
