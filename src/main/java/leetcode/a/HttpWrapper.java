package leetcode.a;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpWrapper {
    private static final String URL = "http://localhost:9900/upstream/vehicle_messages?amount=%d";

    private static final Gson gson = new Gson();

    public static VehicleUpdate[] getAllUpdates(int amount) {
        try {
            String url = String.format(URL, amount);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(response.body(), VehicleUpdate[].class);
        } catch (Exception e) {
            return new VehicleUpdate[0];
        }
    }
}
