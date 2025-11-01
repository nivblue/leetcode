package leetcode.LeetInfra.problem_fetcher;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class LeetCodeProblemFetcher {
    private static final String GRAPHQL_URL = "https://leetcode.com/graphql";
    private static final Gson gson = new Gson();

    // --- MODIFIED: fetch by slug, now returns ProblemSummary using Gson ---
    public static ProblemSummary getProblem(String titleSlug) throws Exception {
        String query = """
        query questionData($titleSlug: String!) {
          question(titleSlug: $titleSlug) {
            questionFrontendId
            title
            titleSlug
            difficulty
            acRate
            isPaidOnly
            topicTags { name }
          }
        }
        """;

        String body = """
        {"operationName":"questionData",
         "variables":{"titleSlug":"%s"},
         "query":%s}
        """.formatted(titleSlug, toJsonString(query));

        HttpRequest req = HttpRequest.newBuilder(URI.create(GRAPHQL_URL))
                .header("Content-Type", "application/json")
                .header("Referer", "https://leetcode.com")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() >= 400) {
            throw new RuntimeException("HTTP " + resp.statusCode() + ": " + resp.body());
        }

        Gson gson = new Gson();
        JsonObject root = gson.fromJson(resp.body(), JsonObject.class);
        JsonObject q = root.getAsJsonObject("data").getAsJsonObject("question");
        if (q == null || q.isJsonNull()) {
            throw new NoSuchElementException("Problem not found for slug: " + titleSlug);
        }

        ProblemSummary p = new ProblemSummary();
        p.id = q.get("questionFrontendId").getAsString();
        p.title = q.get("title").getAsString();
        p.titleSlug = q.get("titleSlug").getAsString();
        p.difficulty = q.get("difficulty").getAsString();
        p.acRate = q.get("acRate").getAsDouble();
        p.paidOnly = q.get("isPaidOnly").getAsBoolean();

        List<String> tags = new ArrayList<>();
        for (JsonElement t : q.getAsJsonArray("topicTags")) {
            tags.add(t.getAsJsonObject().get("name").getAsString());
        }
        p.topicTags = tags;

        return p;
    }

    // --- NEW: fetch by problem number (frontend id) using Gson, returns ProblemSummary ---
    public static ProblemSummary getProblem(int problemNumber) throws Exception {
        // Use searchKeywords to find by visible (frontend) ID, then pick exact match if present.
        String query = """
        query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
          problemsetQuestionList: questionList(categorySlug: $categorySlug, limit: $limit, skip: $skip, filters: $filters) {
            total: totalNum
            questions: data {
              acRate
              difficulty
              questionFrontendId
              isPaidOnly
              title
              titleSlug
              topicTags { name }
            }
          }
        }
        """;

        String variables = """
        {"categorySlug":"","skip":0,"limit":20,"filters":{"searchKeywords":"%s"}}
        """.formatted(Integer.toString(problemNumber));

        String body = """
        {"operationName":"problemsetQuestionList",
         "variables":%s,
         "query":%s}
        """.formatted(variables, toJsonString(query));

        HttpRequest req = HttpRequest.newBuilder(URI.create(GRAPHQL_URL))
                .header("Content-Type", "application/json")
                .header("Referer", "https://leetcode.com")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() >= 400) {
            throw new RuntimeException("HTTP " + resp.statusCode() + ": " + resp.body());
        }

        Gson gson = new Gson();
        JsonObject root = gson.fromJson(resp.body(), JsonObject.class);
        JsonObject list = root.getAsJsonObject("data").getAsJsonObject("problemsetQuestionList");
        if (list == null || list.isJsonNull()) {
            throw new RuntimeException("Unexpected response: " + resp.body());
        }

        JsonArray arr = list.getAsJsonArray("questions");
        if (arr == null || arr.size() == 0) {
            throw new NoSuchElementException("Problem not found for number: " + problemNumber);
        }

        // Prefer exact frontend id match
        String targetId = Integer.toString(problemNumber);
        JsonObject q = null;
        for (JsonElement e : arr) {
            JsonObject obj = e.getAsJsonObject();
            if (targetId.equals(obj.get("questionFrontendId").getAsString())) {
                q = obj;
                break;
            }
        }
        if (q == null) {
            // Fallback to first result
            q = arr.get(0).getAsJsonObject();
        }

        ProblemSummary p = new ProblemSummary();
        p.id = q.get("questionFrontendId").getAsString();
        p.title = q.get("title").getAsString();
        p.titleSlug = q.get("titleSlug").getAsString();
        p.difficulty = q.get("difficulty").getAsString();
        p.acRate = q.get("acRate").getAsDouble();
        p.paidOnly = q.get("isPaidOnly").getAsBoolean();

        List<String> tags = new ArrayList<>();
        for (JsonElement t : q.getAsJsonArray("topicTags")) {
            tags.add(t.getAsJsonObject().get("name").getAsString());
        }
        p.topicTags = tags;

        return p;
    }

    private static String toJsonString(String s) {
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\"";
    }


    /**
     * Fetch ALL problems for a given difficulty.
     * difficulty must be "EASY", "MEDIUM", or "HARD" (case-insensitive).
     */
    public static List<ProblemSummary> getProblemsByDifficulty(String difficulty) throws Exception {
        String level = difficulty.toUpperCase(Locale.ROOT);
        if (!level.equals("EASY") && !level.equals("MEDIUM") && !level.equals("HARD")) {
            throw new IllegalArgumentException("difficulty must be EASY, MEDIUM, or HARD");
        }

        String query = """
        query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
          problemsetQuestionList: questionList(categorySlug: $categorySlug, limit: $limit, skip: $skip, filters: $filters) {
            total: totalNum
            questions: data {
              acRate
              difficulty
              questionFrontendId
              isPaidOnly
              title
              titleSlug
              topicTags { name slug }
            }
          }
        }
        """;

        int limit = 50;
        int skip = 0;
        int total = Integer.MAX_VALUE;

        List<ProblemSummary> results = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();

        while (skip < total) {
            String variables = """
            {"categorySlug":"","skip":%d,"limit":%d,"filters":{"difficulty":"%s"}}
            """.formatted(skip, limit, level);

            String body = """
            {"operationName":"problemsetQuestionList",
             "variables":%s,
             "query":%s}
            """.formatted(variables, toJsonString(query));

            HttpRequest req = HttpRequest.newBuilder(URI.create(GRAPHQL_URL))
                    .header("Content-Type", "application/json")
                    .header("Referer", "https://leetcode.com")
                    .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() >= 400) {
                throw new RuntimeException("HTTP " + resp.statusCode() + ": " + resp.body());
            }

            JsonObject root = gson.fromJson(resp.body(), JsonObject.class);
            JsonObject list = root.getAsJsonObject("data")
                    .getAsJsonObject("problemsetQuestionList");

            total = list.get("total").getAsInt();
            for (JsonElement qElem : list.getAsJsonArray("questions")) {
                JsonObject q = qElem.getAsJsonObject();
                ProblemSummary p = new ProblemSummary();
                p.id = q.get("questionFrontendId").getAsString();
                p.title = q.get("title").getAsString();
                p.titleSlug = q.get("titleSlug").getAsString();
                p.difficulty = q.get("difficulty").getAsString();
                p.acRate = q.get("acRate").getAsDouble();
                p.paidOnly = q.get("isPaidOnly").getAsBoolean();

                List<String> tags = new ArrayList<>();
                for (JsonElement tagElem : q.getAsJsonArray("topicTags")) {
                    tags.add(tagElem.getAsJsonObject().get("name").getAsString());
                }
                p.topicTags = tags;

                results.add(p);
            }

            skip += limit;
        }

        return results;
    }


}
