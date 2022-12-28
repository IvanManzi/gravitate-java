package com.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mashape.unirest.http.JsonNode;

public class JiraUtils {

    public static final String JIRA_USERNAME = "nostalgie@hviewtech.com";

    public static final String JIRA_TOKEN = "NH7ccfsEdZFsMCdRqWth928C";

    private static final String API_BASE_URL = "https://highviewtech.atlassian.net/rest/api/3";

    public static JSONObject getAllProjects() throws UnirestException {
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
            JsonNode assignedUsers = getProjectAssignedUsers(projectsArray.getJSONObject(i).get("key").toString());

            projectsArray.getJSONObject(i).getJSONObject("insight").put("totalDoneIssuesCount",doneIssues);
            projectsArray.getJSONObject(i).put("assignedUsers",assignedUsers.getArray());
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


    public static JsonNode getProjectAssignedUsers(String key) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/user/assignable/multiProjectSearch")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .queryString("projectKeys", key)
                .asJson();
        return response.getBody();
    }


    public static JsonNode getTechnologiesUsed(){
        return null;
    }


    public static JsonNode getProjectRisks(){
        return null;
    }

    public static JsonNode getProjectIssues(){
        return null;
    }

    public static Integer getProjectProgress(String key) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL + "/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                //.queryString("query", "query")
                .queryString("jql", "project="+key+" AND status=done")
                .asJson();
        JSONObject object = response.getBody().getObject();
        return object.getInt("total");
    }

    public static void main(String[] args) throws UnirestException {
        System.out.println(getAllProjects());
    }
}
