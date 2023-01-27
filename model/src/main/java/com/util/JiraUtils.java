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

    private static final int CACHE_SIZE = 1000; // Maximum number of entries in the cache
    private static final long ASSIGNED_USER_TASKS_CACHE_TTL = 60 * 1000 * 20; // 20 mins cache expiration time

    private static final long ASSIGNED_USERS_CACHE_TTL = 60 * 60 * 1000 * 1; // 1 hour cache expiration time

    private static final long ALL_PROJECTS_CACHE_TTL = 60 * 1000 * 5; // 5 hour cache expiration time
    private static Map<String, CacheEntry> cache = new LinkedHashMap<>();

    private static Map<String, CacheEntry> cache2 = new LinkedHashMap<>();

    private static Map<String, CacheEntry> projectsCache = new LinkedHashMap<>();



    public static boolean isJiraIdValid(String jiraId) throws UnirestException {
        if(ValidationUtil.isNullObject(jiraId)){
            return false;
        }
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/user")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("accountId", jiraId)
                .asJson();
        System.out.println(response.getBody());
        if(response.getStatus() == 200){
            return true;
        }else{
            return false;
        }
    }



    public static String getAllProjects(List<Map> projects) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        if(projects.isEmpty()){
            return null;
        }
        ArrayList<Integer>  unWantedIndices = new ArrayList<>();
        // Create a HashMap to store the projects with their corresponding ids
        Map<String, Map> projectsById = new HashMap<>();
        for (Map project : projects) {
            String id = (String) project.get("jira_project_key");
            projectsById.put(id, project); 
        }

        CacheEntry cacheEntry = projectsCache.get("ALL_PROJECTS");
        long currentTime = System.currentTimeMillis();
        if (cacheEntry == null || currentTime - cacheEntry.timestamp > ALL_PROJECTS_CACHE_TTL) {
            HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/project/search")
                    .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                    .header("Accept", "application/json")
                    .queryString("expand", "insight,lead,issueTypes")
                    .asJson();
            cacheEntry = new CacheEntry(response.getBody().getObject(), currentTime);
            projectsCache.put("ALL_PROJECTS",cacheEntry);
        }
        JSONObject jsonObject = cacheEntry.data;
        //Deep clone the jsonObject to avoid manipulations of jiraProjectsArray from affecting future method calls
        ObjectMapper mapper1 = new ObjectMapper();
        Map<String, Object> map1 = mapper1.readValue(jsonObject.toString(), new TypeReference<Map<String, Object>>() {});
        JSONObject jsonObjectClone = new JSONObject(mapper1.writeValueAsString(map1));
        JSONArray jiraProjectsArray = jsonObjectClone.getJSONArray("values");

        System.out.println(jiraProjectsArray.length());
        for (int i = 0; i < jiraProjectsArray.length(); i++) {
            //String id = jiraProjectsArray.getJSONObject(i).get("id").toString();
            String key = jiraProjectsArray.getJSONObject(i).get("key").toString();
            // Check if the project has a corresponding id in the HashMap
            if (projectsById.containsKey(key)) {
                System.out.println(key);
                // Check if the result for the given project id is in the cache
                Integer doneIssues = getProjectDoneIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                Integer pendingIssues = getProjectPendingIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                Integer inProgressIssues = getProjectInProgressIssues(jiraProjectsArray.getJSONObject(i).get("key").toString());
                JSONObject assignedUsers = getProjectAssignedUsers1(jiraProjectsArray.getJSONObject(i).get("key").toString());

                jiraProjectsArray.getJSONObject(i).put("projectInfo", projectsById.get(key));
                jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("totalDoneIssuesCount", doneIssues);
                jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("issuesInProgressCounter", inProgressIssues);
                jiraProjectsArray.getJSONObject(i).getJSONObject("insight").put("notStartedCounter", pendingIssues);

                jiraProjectsArray.getJSONObject(i).put("assignedUsers", assignedUsers);
                }else {
                    unWantedIndices.add(i);
                }
            }
            System.out.println(unWantedIndices);
            //remove un wanted indices from jiraProjectsArray
            for (int i = unWantedIndices.size() - 1; i >= 0; i--) {
                jiraProjectsArray.remove(unWantedIndices.get(i));
            }

            ObjectMapper mapper = new ObjectMapper();

            // Convert the JSONObject to a Java object
            Map<String, Object> map = mapper.readValue(jsonObjectClone.toString(), new TypeReference<Map<String, Object>>() {
            });

            // Convert the Java object to a JSON string
            String jsonString = mapper.writeValueAsString(map);

            return jsonString;
    }


    public static JSONObject getProjectAssignedUsers1(String key) throws UnirestException, InterruptedException, ExecutionException {
        // Check if the results are in the cache
        CacheEntry cacheEntry = cache2.get(key);
        long currentTime = System.currentTimeMillis();
        if (cacheEntry == null || currentTime - cacheEntry.timestamp > ASSIGNED_USERS_CACHE_TTL) {
            cache2.clear();
            // Results not in cache or cache entry expired, make the API calls and store the results in the cache
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
            cacheEntry = new CacheEntry(returnObject, currentTime);
            cache2.put(key, cacheEntry);
        }
        return cacheEntry.data;
    }





    public static JSONObject getProjectAssignedUsers(String key) throws UnirestException, InterruptedException, ExecutionException {
        // Check if the results are in the cache
        CacheEntry cacheEntry = cache.get(key);
        long currentTime = System.currentTimeMillis();
        if (cacheEntry == null || currentTime - cacheEntry.timestamp > ASSIGNED_USERS_CACHE_TTL) {
            cache.clear();
            // Results not in cache or cache entry expired, make the API calls and store the results in the cache
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
            cacheEntry = new CacheEntry(returnObject, currentTime);
            cache.put(key, cacheEntry);
        }
        return cacheEntry.data;
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

    public static String getAssignedUsersWithTasks1(String projectKey,String accountId,String taskLabel) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        JSONArray allUsers = new JSONArray();
        JSONArray temp = null;
        JSONArray adminArray = null;
        JSONObject temp2 = null;
        JSONObject data = new JSONObject();
        JSONObject user = new JSONObject();

        JSONObject jsonObject = getProjectAssignedUsers(projectKey);
        //System.out.println(jsonObject);
        if(jsonObject.has("Administrator")){
            if(jsonObject.getJSONObject("Administrator").getJSONArray("actors").length() > 0){
                allUsers = jsonObject.getJSONObject("Administrator").getJSONArray("actors");
                for(int i = 0; i <allUsers.length(); i++){
                    if(allUsers.getJSONObject(i).has("actorUser")){ //ignore actorGroup users
                        continue;
                    }else{
                        allUsers.remove(i);
                    }
                }
            }
        }
        //System.out.println(adminArray);

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





        if(!ValidationUtil.isNullObject(allUsers)){
            for(int i =0; i<allUsers.length(); i++){
                if(!ValidationUtil.isNullObject(accountId)){//retrieve tasks for specified user
                    if(allUsers.getJSONObject(i).has("actorUser")){
                        temp2 = allUsers.getJSONObject(i).getJSONObject("actorUser");
                        if(temp2.get("accountId").equals(accountId)){
                            user.put("tasksInfo",getUserProjectTasks1(projectKey, (String) temp2.get("accountId"),taskLabel));
                            break ;
                        }
                    }
                }else{
                    if(allUsers.getJSONObject(i).has("actorUser")){
                        temp2 = allUsers.getJSONObject(i).getJSONObject("actorUser");
                        allUsers.getJSONObject(i).put("tasksInfo",getUserProjectTasks1(projectKey, (String) temp2.get("accountId"),taskLabel));
                    }
                }
            }
        }else{
            //project has no assigned users
            allUsers = new JSONArray();
            JSONObject taskInfo = new JSONObject();
            taskInfo.put("taskInfo",new JSONObject());
            taskInfo.put("tasks",new JSONArray());
            allUsers.put(0,taskInfo);
        }



        if(!ValidationUtil.isNullObject(accountId)){
            data.put("data",user);
        }
        else{
            data.put("data",allUsers);
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(data.toString(), new TypeReference<Map<String, Object>>() {});

        String jsonString = mapper.writeValueAsString(map);

        return jsonString;
    }

    public static JSONObject getUserProjectTasks1(String projectKey, String accountId, String taskLabel) throws ExecutionException, InterruptedException, JsonProcessingException {
        JSONObject temp = null;
        JSONObject temp2 = null;
        JSONArray taskLabels = null;
        JSONObject returnObject = new JSONObject();
        JSONObject statsObj = new JSONObject();
        int doneCounter = 0;
        int inProgressCounter = 0;
        int notStartedCounter = 0;

        // Key for the cache
        String cacheKey = projectKey + "_" + accountId;
        // Check if the results are in the cache
        CacheEntry cacheEntry = cache.get(cacheKey);
        long currentTime = System.currentTimeMillis();
        if (cacheEntry == null || currentTime - cacheEntry.timestamp > ASSIGNED_USER_TASKS_CACHE_TTL) {
            cache.clear();
            // Results not in cache or cache entry expired, make the HTTP request and store the results in the cache
            HttpResponse<JsonNode> response = Unirest.get("https://highviewtech.atlassian.net/rest/api/2/search")
                    .header("Authorization", JIRA_AUTH_HEADER)
                    .queryString("jql", "project="+projectKey + " AND assignee ="+accountId)
                    .asJsonAsync().get();
            JSONObject data = response.getBody().getObject();
            cacheEntry = new CacheEntry(data, currentTime);
            cache.put(cacheKey, cacheEntry);
        }
        JSONArray userTasks = new JSONArray();

        //check cache has issues key
        if(!cacheEntry.data.has("issues")){
            statsObj.put("doneCounter", doneCounter);
            statsObj.put("inProgressCounter", inProgressCounter);
            statsObj.put("notStartedCounter", notStartedCounter);
            returnObject.put("tasks", userTasks);
            returnObject.put("stats", statsObj);

            return returnObject;
        }

        JSONArray issues = cacheEntry.data.getJSONArray("issues");

        //check if task label string is empty
        if(!ValidationUtil.isNullObject(taskLabel)){
            for (int i = 0; i < issues.length(); i++){
                temp = (JSONObject) issues.getJSONObject(i).getJSONObject("fields").get("status");
                temp2 =  issues.getJSONObject(i).getJSONObject("fields");
                //temp2 = (JSONObject) issues.getJSONObject(i).getJSONObject("fields").getJSONArray("status");
                if(temp2.has("labels")){
                    taskLabels = temp2.getJSONArray("labels");
                    if(checkIfTaskLabelExists(taskLabels,taskLabel)){
                        if(temp.get("name").equals("Done")){
                            doneCounter++;
                        }
                        if(temp.get("name").equals("To Do")){
                            notStartedCounter++;
                        }
                        if(temp.get("name").equals("In Progress")){
                            inProgressCounter++;
                        }
                        userTasks.put(issues.getJSONObject(i));
                    }
                }

            }
        }else{
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
                userTasks.put(issues.getJSONObject(i));
            }
        }

        statsObj.put("doneCounter", doneCounter);
        statsObj.put("inProgressCounter", inProgressCounter);
        statsObj.put("notStartedCounter", notStartedCounter);

        returnObject.put("tasks", userTasks);
        returnObject.put("stats", statsObj);



        return returnObject;
    }

    /*public static JSONObject getUserProjectTasks(String projectKey,String accountId,String taskLabel) throws ExecutionException, InterruptedException {
        JSONObject data = null;
        JSONObject temp = null;
        JSONObject temp2 = null;
        JSONArray taskLabels = null;
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

        //check if task label string is empty
        if(!ValidationUtil.isNullObject(taskLabel)){
            for (int i = 0; i < issues.length(); i++){
                temp = (JSONObject) issues.getJSONObject(i).getJSONObject("fields").get("status");
                temp2 =  issues.getJSONObject(i).getJSONObject("fields");
                //temp2 = (JSONObject) issues.getJSONObject(i).getJSONObject("fields").getJSONArray("status");
                if(temp2.has("labels")){
                    taskLabels = temp2.getJSONArray("labels");
                    if(checkIfTaskLabelExists(taskLabels,taskLabel)){
                        if(temp.get("name").equals("Done")){
                            doneCounter++;
                        }
                        if(temp.get("name").equals("To Do")){
                            notStartedCounter++;
                        }
                        if(temp.get("name").equals("In Progress")){
                            inProgressCounter++;
                        }
                        userTasks.put(issues.getJSONObject(i));
                    }
                }

            }
        }else{
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
                userTasks.put(issues.getJSONObject(i));
            }
        }




        statsObj.put("doneCounter",doneCounter);
        statsObj.put("inProgressCounter",inProgressCounter);
        statsObj.put("notStartedCounter",notStartedCounter);

        returnObject.put("tasks",userTasks);
        returnObject.put("stats",statsObj);



        return returnObject;
    }*/

    public static boolean checkIfTaskLabelExists(JSONArray taskLabels,String label){
        for(int i = 0; i < taskLabels.length(); i++){
            if(taskLabels.get(i).equals(label)){
                return true;
            }
        }
        return false;
    }


    public static String deleteIssue(String issueId) throws UnirestException, JsonProcessingException {
        JSONObject returnObject = new JSONObject();
        HttpResponse<String> response = Unirest.delete(API_BASE_URL+"/issue/{issueIdOrKey}")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .routeParam("issueIdOrKey",issueId)
                .queryString("deleteSubtasks",true)
                .asString();


        if(response.getStatus() == 204){
            returnObject.put("message", "Issue successfully deleted. ");
        }
        else if(response.getStatus() == 401){
            returnObject.put("message", "Incorrect Credentials. ");
        }
        else if(response.getStatus() == 403){
            returnObject.put("message", "Unauthorised to perform this action. ");
        }
        else if(response.getStatus() == 404){
            returnObject.put("message", "Issue not found. ");
        }else{
            returnObject.put("message", "error executing request. Try again.");
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});
        return mapper.writeValueAsString(map);

    }

    public static String createIssue(String projectKey, String summary, String description, String issueType, String assigneeAccountId, List<String> labels) throws UnirestException, JsonProcessingException {
        JSONObject returnObject = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONObject projectJson = new JSONObject();
        JSONObject assigneeJson = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject issueTypeJson = new JSONObject();

        String jsonString = "{\"type\": \"" + "doc" + "\",\"version\": " + "1" + ",\"content\": [{\"type\": \"" + "paragraph" + "\",\"content\": [{\"text\": \"" + description + "\",\"type\": \"" + "text" + "\"}]}]}}";
        JSONObject descriptionJson = new JSONObject(jsonString);
        String labelsJson = "";
        if (labels != null && !labels.isEmpty()) {
            labelsJson = ",\"labels\": [";
            for (int i = 0; i < labels.size(); i++) {
                labelsJson += "\"" + labels.get(i) + "\"";
                if (i < labels.size() - 1) {
                    labelsJson += ",";
                }
            }
            labelsJson += "]";
        }


        projectJson.put("id",projectKey);
        issueTypeJson.put("id",issueType);
        assigneeJson.put("id",assigneeAccountId);

        fields.put("summary",summary);
        fields.put("description",descriptionJson);
        fields.put("labels",labels);
        fields.put("issuetype",issueTypeJson);
        fields.put("project",projectJson);
        fields.put("assignee",assigneeJson);

        payload.put("fields",fields);

        //System.out.println(payload);

        HttpResponse<JsonNode> response = Unirest.post(API_BASE_URL+"/issue")
                .header("Content-Type", "application/json")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .body(payload)
                .asJson();
        //System.out.println(response.getStatus());
        //System.out.println(response.getBody());
        if(response.getStatus() == 201){
            returnObject.put("message", "Issue successfully created. ");
        }else{
            returnObject = response.getBody().getObject();
        }
        //System.out.println(returnObject);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});
        return mapper.writeValueAsString(map);
    }


    public static String updateIssue(String issueKey,String projectKey, String summary, String description, String issueType, String assigneeAccountId, List<String> labels) throws UnirestException, JsonProcessingException {
        JSONObject returnObject = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONObject projectJson = new JSONObject();
        JSONObject assigneeJson = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject issueTypeJson = new JSONObject();

        String jsonString = "{\"type\": \"" + "doc" + "\",\"version\": " + "1" + ",\"content\": [{\"type\": \"" + "paragraph" + "\",\"content\": [{\"text\": \"" + description + "\",\"type\": \"" + "text" + "\"}]}]}}";
        JSONObject descriptionJson = new JSONObject(jsonString);
        String labelsJson = "";
        if (labels != null && !labels.isEmpty()) {
            labelsJson = ",\"labels\": [";
            for (int i = 0; i < labels.size(); i++) {
                labelsJson += "\"" + labels.get(i) + "\"";
                if (i < labels.size() - 1) {
                    labelsJson += ",";
                }
            }
            labelsJson += "]";
        }


        projectJson.put("id",projectKey);
        issueTypeJson.put("id",issueType);
        assigneeJson.put("id",assigneeAccountId);

        fields.put("summary",summary);
        fields.put("description",descriptionJson);
        fields.put("labels",labels);
        fields.put("issuetype",issueTypeJson);
        fields.put("project",projectJson);
        fields.put("assignee",assigneeJson);

        payload.put("fields",fields);
        HttpResponse<JsonNode> response = Unirest.put(API_BASE_URL+"/issue/"+ issueKey)
                .header("Content-Type", "application/json")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .body(payload)
                .asJson();
        System.out.println(response.getBody());
        System.out.println(response.getStatus());
        if(response.getStatus() == 204){
            returnObject.put("message", "Issue successfully updated. ");
        }
        else if(response.getStatus() == 401){
            returnObject.put("message", "Incorrect Credentials. ");
        }
        else if(response.getStatus() == 403){
            returnObject.put("message", "Unauthorised to perform this action. ");
        }
        else if(response.getStatus() == 404){
            returnObject.put("message", "Issue not found. ");
        }else{
            returnObject.put("message", "error executing request. Try again.");
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});
        return mapper.writeValueAsString(map);
    }

    public static String getProjectIssueTypes(String projectKey) throws UnirestException, JsonProcessingException {
        JSONObject returnObject = new JSONObject();
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/issuetype/project")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("projectId", projectKey)
                .asJson();

        returnObject = response.getBody().getObject();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});
        //System.out.println(mapper.writeValueAsString(map));
        return mapper.writeValueAsString(map);
    }


    public static String createJiraProject(String name,String projectKey, String description, String leadAccountId) throws UnirestException, JsonProcessingException {
        JSONObject payload = new JSONObject();
        JSONObject returnObject = new JSONObject();


        payload.put("name",name);
        payload.put("description",description);
        payload.put("leadAccountId",leadAccountId);
        payload.put("key",projectKey);
        payload.put("assigneeType","PROJECT_LEAD");
        payload.put("projectTemplateKey","com.pyxis.greenhopper.jira:gh-simplified-agility-scrum");
        payload.put("projectTypeKey","software");

        HttpResponse<JsonNode> response = Unirest.post(API_BASE_URL+"/project")
                .header("Content-Type", "application/json")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .body(payload)
                .asJson();

        if(response.getStatus() == 201){
            returnObject.put("message", "Project successfully created. ");
            //add project lead to project role
            assignProjectToUser(projectKey,leadAccountId);
        }
        else if(response.getStatus() == 401){
            returnObject.put("message", "Incorrect Credentials. ");
        }
        else if(response.getStatus() == 403){
            returnObject.put("message", "Unauthorised to perform this action. ");
        }
        else if(response.getStatus() == 404){
            returnObject.put("message", "Issue not found. ");
        }else{
            returnObject = response.getBody().getObject();
        }
        System.out.println(returnObject);
        System.out.println(response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});
        return mapper.writeValueAsString(map);
    }

    public static String updateJiraProject(String projectKey,String name,String description, String leadAccountId) throws UnirestException, JsonProcessingException {
        JSONObject payload = new JSONObject();
        JSONObject returnObject = new JSONObject();

        payload.put("name",name);
        payload.put("description",description);
        payload.put("leadAccountId",leadAccountId);
        payload.put("assigneeType","PROJECT_LEAD");
        payload.put("projectTypeKey","business");

        HttpResponse<JsonNode> response = Unirest.put(API_BASE_URL+"/project/"+projectKey)
                .header("Content-Type", "application/json")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .body(payload)
                .asJson();
        if(response.getStatus() == 200){
            returnObject.put("message", "Project successfully created. ");
        }
        else if(response.getStatus() == 401){
            returnObject.put("message", "Incorrect Credentials. ");
        }
        else if(response.getStatus() == 403){
            returnObject.put("message", "Unauthorised to perform this action. ");
        }
        else if(response.getStatus() == 404){
            returnObject.put("message", "Issue not found. ");
        }else{
            returnObject.put("message", "error executing request. Try again.");
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});

        return mapper.writeValueAsString(map);
    }


    public static String deleteJiraProject(String projectKey) throws UnirestException, JsonProcessingException {
        JSONObject returnObject = new JSONObject();
        HttpResponse<JsonNode> response = Unirest.delete(API_BASE_URL+"/project/{projectKey}")
                .header("Content-Type", "application/json")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .routeParam("projectKey",projectKey)
                .asJson();

        if(response.getStatus() == 204){
            returnObject.put("message", "Issue successfully deleted. ");
        }
        else if(response.getStatus() == 401){
            returnObject.put("message", "Incorrect Credentials. ");
        }
        else if(response.getStatus() == 404){
            returnObject.put("message", "Issue not found. ");
        }else{
            returnObject.put("message", "error executing request. Try again.");
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(returnObject.toString(), new TypeReference<Map<String, Object>>() {});
        return mapper.writeValueAsString(map);
    }

    public static boolean assignProjectToUser(String projectKey,String accountId) throws UnirestException {
        JSONObject returnOBJ = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONArray array = new JSONArray();
        array.put(0,accountId);
        payload.put("user",array);

        //get project roles
        HttpResponse<JsonNode> rolesResponse = Unirest.get(API_BASE_URL + "/project/{projectKey}/role")
                .header("accept", "application/json")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .routeParam("projectKey", projectKey)
                .asJson();

        JSONObject jsonObject = rolesResponse.getBody().getObject();
        String url = (String) jsonObject.get("Administrator");
        String[] parts = url.split("/");
        // The ID is the last element in the array
        String roleId = parts[parts.length - 1];

        HttpResponse<JsonNode> response = Unirest.post(API_BASE_URL + "/project/{projectKey}/role/{roleId}")
                .header("Content-Type", "application/json")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .routeParam("projectKey", projectKey)
                .routeParam("roleId",roleId)
                .body(payload)
                .asJson();
        System.out.println(response.getBody().getObject());
        int status = response.getStatus();
        if (status == 200) {
            return true;
        } else {
            return false;
        }

    }


    public static void main(String[] args) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        List<String> labels = new ArrayList<>();
        labels.add("testing");
        labels.add("launch");
        updateIssue("10158","10001","UPDATED BY TERMINAL","UPDATED TO X DESCRIPTION","10006","633aed432eaaa5dcfa163fbd",labels);
    }


}
