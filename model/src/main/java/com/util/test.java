package com.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class test {

    private static final int CACHE_SIZE = 1000; // Maximum number of entries in the cache


    public static final String JIRA_USERNAME = "nostalgie@hviewtech.com";

    public static final String JIRA_TOKEN = "NH7ccfsEdZFsMCdRqWth928C";

    private static final String JIRA_AUTH_HEADER = "Basic " + Base64.getEncoder().encodeToString((JIRA_USERNAME+":"+JIRA_TOKEN).getBytes());


    private static final long CACHE_TTL = 60 * 60 * 1000; // 1 hour cache expiration time
    private static Map<String, CacheEntry> cache = new LinkedHashMap<>();

    public static void getAllProjectTasks(String projectKey) throws ExecutionException, InterruptedException {

        String cacheKey = "_" + projectKey;
        CacheEntry cacheEntry = cache.get(cacheKey);
        long currentTime = System.currentTimeMillis();
        if (cacheEntry == null || currentTime - cacheEntry.timestamp > CACHE_TTL) {
            System.out.println("un cached.");
            // Results not in cache or cache entry expired, make the HTTP requests and store the results in the cache
            JSONObject data = new JSONObject();
            JSONArray issues = new JSONArray();
            int startAt = 0;
            while (true) {
                HttpResponse<JsonNode> response = Unirest.get("https://highviewtech.atlassian.net/rest/api/2/search")
                        .header("Authorization", JIRA_AUTH_HEADER)
                        .queryString("jql", "project="+projectKey)
                        .queryString("startAt", startAt)
                        .queryString("maxResults", 50)
                        .asJsonAsync().get();
                JSONArray retrievedIssues = response.getBody().getObject().getJSONArray("issues");
                if (retrievedIssues.length() == 0) {
                    // No more tasks to retrieve
                    break;
                }
                for (int i = 0; i < retrievedIssues.length(); i++) {
                    issues.put(retrievedIssues.get(i));
                }
                startAt += 50;
            }
            data.put("issues", issues);
            cacheEntry = new CacheEntry(data, currentTime);
            cache.put(cacheKey, cacheEntry);
        }
    }




}
