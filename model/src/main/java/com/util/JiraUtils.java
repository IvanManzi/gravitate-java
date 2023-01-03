package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mashape.unirest.http.JsonNode;

import java.util.*;
import java.util.concurrent.*;

public class JiraUtils {

    public static final String JIRA_USERNAME = "nostalgie@hviewtech.com";

    public static final String JIRA_TOKEN = "NH7ccfsEdZFsMCdRqWth928C";

    private static final String API_BASE_URL = "https://highviewtech.atlassian.net/rest/api/3";

    private static final String JIRA_AUTH_HEADER = "Basic " + Base64.getEncoder().encodeToString((JIRA_USERNAME+":"+JIRA_TOKEN).getBytes());



    public static String getAllProjects(List<Map> projects) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        // Create a cache to store the results of the API calls, with entries expiring after one hour
        Cache<String, Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .maximumSize(1000)
                .build();

        // Create a HashMap to store the projects with their corresponding ids
        Map<String, Map> projectsById = new HashMap<>();
        for (Map project : projects) {
            String id = (String) project.get("jira_id");
            projectsById.put(id, project);
        }

        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/project/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("expand", "insight,lead,issueTypes")
                .asJson();
        JSONObject jsonObject = response.getBody().getObject();
        JSONArray jiraProjectsArray = jsonObject.getJSONArray("values");
        for (int i = 0; i < jiraProjectsArray.length(); i++) {
            String id = jiraProjectsArray.getJSONObject(i).get("id").toString();
            // Check if the project has a corresponding id in the HashMap
            if (projectsById.containsKey(id)) {
                // Check if the result for the given project id is in the cache
                Integer doneIssues = (Integer) cache.getIfPresent("done_" + id);
                if (doneIssues == null) {
                    // If the result is not in the cache, get it from the API and store it in the cache
                    doneIssues = getProjectDoneIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                    cache.put("done_" + id, doneIssues);
                }
                Integer pendingIssues = (Integer) cache.getIfPresent("pending_" + id);
                if (pendingIssues == null) {
                    pendingIssues = getProjectPendingIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                    cache.put("pending_" + id, pendingIssues);
                }
                Integer inProgressIssues = (Integer) cache.getIfPresent("in_progress_" + id);
                if (inProgressIssues == null) {
                    inProgressIssues = getProjectInProgressIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                    cache.put("in_progress_" + id, inProgressIssues);
                    JSONObject assignedUsers = (JSONObject) cache.getIfPresent("assigned_" + id);
                    if (assignedUsers == null) {
                        assignedUsers = getProjectAssignedUsers(jiraProjectsArray.getJSONObject(i).get("key").toString());
                        cache.put("assigned_" + id, assignedUsers);
                    }

                    jiraProjectsArray.getJSONObject(i).put("projectInfo", projectsById.get(id));
                    jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("totalDoneIssuesCount", doneIssues);
                    jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("issuesInProgressCounter", inProgressIssues);
                    jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("notStartedCounter", pendingIssues);

                    jiraProjectsArray.getJSONObject(i).put("assignedUsers", assignedUsers);
                } else {
                    jiraProjectsArray.remove(i);
                }
            }
        }
            ObjectMapper mapper = new ObjectMapper();

            // Convert the JSONObject to a Java object
            Map<String, Object> map = mapper.readValue(jsonObject.toString(), new TypeReference<Map<String, Object>>() {
            });

            // Convert the Java object to a JSON string
            String jsonString = mapper.writeValueAsString(map);

            return jsonString;
    }


        public static String getAllProjects1(List<Map> projects) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        // Create a HashMap to store the projects with their corresponding ids
        Map<String, Map> projectsById = new HashMap<>();
        for (Map project : projects) {
            String id = (String) project.get("jira_id");
            projectsById.put(id, project);
        }

        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL+"/project/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("expand","insight,lead,issueTypes")
                .asJson();
        JSONObject jsonObject = response.getBody().getObject();
        JSONArray jiraProjectsArray = jsonObject.getJSONArray("values");
        for (int i = 0; i < jiraProjectsArray.length(); i++){
            String id = jiraProjectsArray.getJSONObject(i).get("id").toString();
            // Check if the project has a corresponding id in the HashMap
            if (projectsById.containsKey(id)) {
                Integer doneIssues = getProjectDoneIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                Integer pendingIssues = getProjectPendingIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                Integer inProgressIssues = getProjectInProgressIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());

                JSONObject assignedUsers = getProjectAssignedUsers(jiraProjectsArray.getJSONObject(i).get("key").toString());
                jiraProjectsArray.getJSONObject(i).put("projectInfo", projectsById.get(id));
                jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("totalDoneIssuesCount",doneIssues);
                jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("issuesInProgressCounter",inProgressIssues);
                jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("notStartedCounter",pendingIssues);

                jiraProjectsArray.getJSONObject(i).put("assignedUsers", assignedUsers);
            }else{
                jiraProjectsArray.remove(i);
            }
        }
        ObjectMapper mapper = new ObjectMapper();

        // Convert the JSONObject to a Java object
        Map<String, Object> map = mapper.readValue(jsonObject.toString(), new TypeReference<Map<String, Object>>() {});

        // Convert the Java object to a JSON string
        String jsonString = mapper.writeValueAsString(map);

        return jsonString;
    }


    public static JSONObject getProjectInfo(String projectKey) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL+"/project/"+projectKey)
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("expand","description,lead,issueTypes,insight")
                .asJson();
        return response.getBody().getObject();
    }


    public static JSONObject getProjectAssignedUsers(String key) throws UnirestException, InterruptedException, ExecutionException {
        //get all roles
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/project/{projectIdOrKey}/role")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .routeParam("projectIdOrKey", key)
                .header("Accept", "application/json")
                .asJson();
        JSONObject jsonObject = response.getBody().getObject();

        // Create a thread pool with a fixed number of threads
        ExecutorService threadPool = Executors.newFixedThreadPool(jsonObject.length());

        // Create a list of Callable tasks to retrieve the users for each role
        List<Callable<HttpResponse<JsonNode>>> tasks = new ArrayList<>();
        for (Object role : jsonObject.keySet()) {
            String roleUrl = jsonObject.get((String) role).toString();
            //System.out.println(roleUrl);
            Callable<HttpResponse<JsonNode>> task = () -> Unirest.get(roleUrl)
                    .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                    .header("Accept", "application/json")
                    .asJson();
            tasks.add(task);
        }

        // Execute the tasks and retrieve the results
        List<Future<HttpResponse<JsonNode>>> futures = threadPool.invokeAll(tasks);

        // Create the return object and add the results for each role
        JSONObject returnObject = new JSONObject();
        Integer allUsersCount = 0;
        returnObject.put("totalAssignedUsers",allUsersCount);
        for (int i = 1; i < jsonObject.length(); i++) { //initialised i = 1 to skip the atlassian-addons-project-access role actors
            Object role = jsonObject.keySet().toArray()[i];
            allUsersCount += futures.get(i).get().getBody().getObject().getJSONArray("actors").length();
            returnObject.put((String) role, futures.get(i).get().getBody().getObject());
        }
        returnObject.put("totalAssignedUsers",allUsersCount);
        // Shutdown thread pool
        threadPool.shutdown();
        return returnObject;
    }


    public static JsonNode getTechnologiesUsed(){
        return null;
    }


    public static JsonNode getTeamMemberTasks(String accountId){
        return null;
    }

    public static JsonNode getProjectTasks(String projectKey) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL+"/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("jql","type=Task AND project="+projectKey)
                .asJson();
        return response.getBody();
    }

    public static Integer getProjectDoneIssues(String key) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("jql", "project="+key+" AND status=Done")
                .asJson();
        JSONObject object = response.getBody().getObject();
        return object.getInt("total");
    }

    public static Integer getProjectPendingIssues(String key) throws UnirestException {
        String status = "\"To Do\"/";
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("jql", "project="+key+" AND status="+status.replaceAll("[/\\\\]", ""))
                .asJson();
        JSONObject object = response.getBody().getObject();
        return object.getInt("total");
    }

    public static Integer getProjectInProgressIssues(String key) throws UnirestException {
        String status = "\"In Progress\"/";
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("jql", "project="+key+" AND status="+status.replaceAll("[/\\\\]", ""))
                .asJson();
        JSONObject object = response.getBody().getObject();
        return object.getInt("total");
    }

    public static String getAssignedUsersWithTasks1(String projectKey) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        JSONArray allUsers = null;
        JSONArray temp = null;
        JSONObject temp2 = null;
        JSONObject data = new JSONObject();
        HashMap<String,Object> adminUserTasks = null;
        HashMap<String,Object> memberUserTasks = null;

        JSONObject jsonObject = getProjectAssignedUsers(projectKey);
        if(jsonObject.has("Administrator")){
            if(jsonObject.getJSONObject("Administrator").getJSONArray("actors").length() > 0){
                allUsers = jsonObject.getJSONObject("Administrator").getJSONArray("actors");
            }
        }
        if(jsonObject.has("Member")){
            if(jsonObject.getJSONObject("Member").getJSONArray("actors").length() > 0){
                int position = allUsers.length();
                temp = jsonObject.getJSONObject("Member").getJSONArray("actors");
                for(int i = 0; i < temp.length(); i++){
                    allUsers.put(position,temp.getJSONObject(i));
                    position++;
                }
            }
        }

        //System.out.println(allUsers);

        for(int i =0; i<allUsers.length(); i++){
            temp2 = allUsers.getJSONObject(i).getJSONObject("actorUser");
            allUsers.getJSONObject(i).put("tasksInfo",getUserProjectTasks(projectKey, (String) temp2.get("accountId")));
        }
        //System.out.println(allUsers);
        data.put("data",allUsers);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(data.toString(), new TypeReference<Map<String, Object>>() {});

        String jsonString = mapper.writeValueAsString(map);

        return jsonString;
    }

    public static JSONObject getUserProjectTasks(String projectKey,String accountId) throws ExecutionException, InterruptedException {
        JSONObject data = null;
        JSONObject temp = null;
        JSONObject returnObject = new JSONObject();
        JSONObject statsObj = new JSONObject();
        int doneCounter = 0;
        int inProgressCounter = 0;
        int notStartedCounter = 0;



        JSONArray userTasks = new JSONArray();
        HttpResponse<JsonNode> response = Unirest.get("https://highviewtech.atlassian.net/rest/api/2/search")
                .header("Authorization", JIRA_AUTH_HEADER)
                .queryString("jql", "project="+projectKey+" AND assignee="+accountId)
                .asJsonAsync().get();
        data = response.getBody().getObject();
        JSONArray issues = data.getJSONArray("issues");
        for (int i = 0; i < issues.length(); i++){
            temp = (JSONObject) issues.getJSONObject(i).getJSONObject("fields").get("status");
            if(temp.get("name").equals("Done")){
                doneCounter++;
            }
            if(temp.get("name").equals("To Do")){
                notStartedCounter++;
            }
            if(temp.get("name").equals("In Progress")){
                inProgressCounter++;
            }
            userTasks.put(issues.getJSONObject(i).get("fields"));
        }

        statsObj.put("doneCounter",doneCounter);
        statsObj.put("inProgressCounter",inProgressCounter);
        statsObj.put("notStartedCounter",notStartedCounter);

        returnObject.put("tasks",userTasks);
        returnObject.put("stats",statsObj);

        return returnObject;
    }

    private static final int CACHE_SIZE = 1000; // Maximum number of entries in the cache
    private static final long CACHE_TTL = 60 * 60 * 1000; // 1 hour cache expiration time
    private static Map<String, CacheEntry> cache = new LinkedHashMap<>();

    public static String getUserProjectTasks1(String projectKey, String accountId, String status) throws ExecutionException, InterruptedException, JsonProcessingException {
        JSONObject temp = null;
        JSONObject returnObject = new JSONObject();
        JSONObject statsObj = new JSONObject();
        int doneCounter = 0;
        int inProgressCounter = 0;
        int notStartedCounter = 0;

        // Key for the cache
        String cacheKey = projectKey + "-" + accountId + "-" + status;

        // Check if the results are in the cache
        CacheEntry cacheEntry = cache.get(cacheKey);
        long currentTime = System.currentTimeMillis();
        if (cacheEntry == null || currentTime - cacheEntry.timestamp > CACHE_TTL) {
            // Results not in cache or cache entry expired, make the HTTP request and store the results in the cache
            HttpResponse<JsonNode> response = Unirest.get("https://highviewtech.atlassian.net/rest/api/2/search")
                    .header("Authorization", JIRA_AUTH_HEADER)
                    .queryString("jql", "project="+projectKey+" AND assignee="+accountId)
                    .asJsonAsync().get();
            JSONObject data = response.getBody().getObject();
            cacheEntry = new CacheEntry(data, currentTime);
            cache.put(cacheKey, cacheEntry);
        }


        JSONArray userTasks = new JSONArray();
        JSONArray issues = cacheEntry.data.getJSONArray("issues");
        for (int i = 0; i < issues.length(); i++) {
            temp = (JSONObject) issues.getJSONObject(i).getJSONObject("fields").get("status");
            if (temp.get("name").equals("Done")) {
                doneCounter++;
            }
            if (temp.get("name").equals("To Do")) {
                notStartedCounter++;
            }
            if (temp.get("name").equals("In Progress")) {
                inProgressCounter++;
            }
            //check if status is not null
            if (!ValidationUtil.isNullObject(status)) {
                if (temp.get("name").equals(status)) {
                    userTasks.put(issues.getJSONObject(i).get("fields"));
                    //userTasks.put(i,issues.getJSONObject(i).get("fields"));
                }
            } else {
                userTasks.put(issues.getJSONObject(i).get("fields"));
            }
        }

        statsObj.put("doneCounter", doneCounter);
        statsObj.put("inProgressCounter", inProgressCounter);
        statsObj.put("notStartedCounter", notStartedCounter);

        returnObject.put("tasks", userTasks);
        returnObject.put("stats", statsObj);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});

        String jsonString = mapper.writeValueAsString(map);

        return jsonString;
    }





    public static void main(String[] args) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        System.out.println(getProjectPendingIssues("GR"));
    }
}
