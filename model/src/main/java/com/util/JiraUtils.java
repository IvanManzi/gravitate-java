package com.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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

    public static JSONObject getAllProjects() throws UnirestException, ExecutionException, InterruptedException {
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL+"/project/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("expand","insight,lead,issueTypes")
                .asJson();
        JSONObject jsonObject = response.getBody().getObject();
        JSONArray projectsArray = jsonObject.getJSONArray("values");
        for(int i = 0; i < projectsArray.length(); i++){
            //TODO: Add assigned users, add done issues in insights array, add components(Technologies), add project start date,add project risks
            Integer doneIssues = getProjectProgress(projectsArray.getJSONObject(i).get("key").toString());
            JSONObject assignedUsers = getProjectAssignedUsers(projectsArray.getJSONObject(i).get("key").toString());

            projectsArray.getJSONObject(i).getJSONObject("insight").put("totalDoneIssuesCount",doneIssues);
            projectsArray.getJSONObject(i).put("assignedUsers",assignedUsers);
        }
        return jsonObject;
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
            System.out.println(roleUrl);
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
        for (int i = 0; i < jsonObject.length(); i++) {
            Object role = jsonObject.keySet().toArray()[i];
            returnObject.put((String) role, futures.get(i).get().getBody().getObject());
        }

        // Shutdown the thread pool
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

    public static Integer getProjectProgress(String key) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("jql", "project="+key+" AND status=done")
                .asJson();
        JSONObject object = response.getBody().getObject();
        return object.getInt("total");
    }

    public static JSONArray getAssignedUsersWithTasks1(String projectKey) throws UnirestException, ExecutionException, InterruptedException {
        JSONArray allUsers = null;
        JSONArray temp = null;
        JSONObject temp2 = null;
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
        System.out.println(allUsers);

        return null;
    }

    public static JSONObject getUserProjectTasks(String projectKey,String accountId) throws ExecutionException, InterruptedException {
        JSONObject data = null;
        JSONObject temp = null;
        JSONObject returnObject = new JSONObject();
        JSONObject statsObj = new JSONObject();
        int doneCounter = 0;
        int inProgressCounter = 0;
        int highPriorityCounter = 0;



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
                inProgressCounter++;
            }
            userTasks.put(i,issues.getJSONObject(i).get("fields"));
        }

        statsObj.put("doneCounter",doneCounter);
        statsObj.put("inProgressCounter",inProgressCounter);
        statsObj.put("highPriorityCounter",highPriorityCounter);

        returnObject.put("tasks",userTasks);
        returnObject.put("stats",statsObj);
        return returnObject;
    }

    /*public static JsonNode getAssignedUsersWithTasks(String projectKey) throws UnirestException, ExecutionException, InterruptedException {
        // Make a GET request to the Jira REST API to get all issues in the specified project
        HttpResponse<JsonNode> response = Unirest.get("https://highviewtech.atlassian.net/rest/api/2/search")
                .header("Authorization", JIRA_AUTH_HEADER)
                .queryString("jql", "project = " + projectKey)
                .asJsonAsync().get();

        JsonNode body = response.getBody();
        JsonNode issues = body.get("issues");

        // Create a map to store the user assignments and task statuses
        Map<String, List<String>> userAssignments = new HashMap<>();

        // Iterate through the issues and add the assignments and task statuses to the map
        for (int i = 0; i < issues.size(); i++) {
            JsonNode issue = issues.get(i);
            JsonNode fields = issue.get("fields");
            JsonNode assignee = fields.get("assignee");

            // If the issue is not assigned, skip it
            if (assignee == null) {
                continue;
            }

            String name = assignee.get("name").asText();
            String status = fields.get("status").get("name").asText();

            if (!userAssignments.containsKey(name)) {
                userAssignments.put(name, new ArrayList<>());
            }
            userAssignments.get(name).add(status);
        }

        return new JsonNode(userAssignments);
    }*/






    public static void main(String[] args) throws UnirestException, ExecutionException, InterruptedException {
        getAllProjects();
    }
}
