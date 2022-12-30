package com.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JiraProject {
    private String name;
    private List<String> assignedUsers;
    private Date startDate;
    private Integer progress;
    private List<String> technologiesUsed;

    public JiraProject(JSONObject projectJson) {
        // Retrieve project details from the JSON object
        this.name = projectJson.getString("name");
        this.startDate = new Date(projectJson.getLong("created"));
        this.progress = projectJson.getInt("progress");

        // Retrieve assigned users for the project
        JSONArray assigneeArray = projectJson.getJSONArray("assignee");
        this.assignedUsers = new ArrayList<>();
        for (int i = 0; i < assigneeArray.length(); i++) {
            this.assignedUsers.add(assigneeArray.getString(i));
        }

        // Retrieve technologies used for the project (if any)
        JSONArray componentArray = projectJson.getJSONArray("components");
        this.technologiesUsed = new ArrayList<>();
        for (int i = 0; i < componentArray.length(); i++) {
            this.technologiesUsed.add(componentArray.getString(i));
        }
    }

    // Getter methods for the various project properties
    public String getName() { return name; }
    public List<String> getAssignedUsers() { return assignedUsers; }
    public Date getStartDate() { return startDate; }
    public Integer getProgress() { return progress; }
    public List<String> getTechnologiesUsed() { return technologiesUsed; }

    public static void main(String[] args) throws UnirestException {
        // Set up your Jira credentials and API base URL
        String API_BASE_URL = "https://highviewtech.atlassian.net/rest/api/2";
        String JIRA_USERNAME = "nostalgie@hviewtech.com";
        String JIRA_TOKEN = "NH7ccfsEdZFsMCdRqWth928C";

        // Send a GET request to the /project/search endpoint to retrieve all projects
        HttpResponse<JsonNode> response = Unirest.get(API_BASE_URL+"/project/search")
                .basicAuth(JIRA_USERNAME, JIRA_TOKEN)
                .header("Accept", "application/json")
                .asJson();

        // Parse the response to get a list of JSON objects representing the projects
        JSONArray projectsJson = response.getBody().getArray();

       // Create a list of JiraProject objects from the JSON objects
        List<JiraProject> jiraProjects = new ArrayList<>();
        for (int i = 0; i < projectsJson.length(); i++) {
            jiraProjects.add(new JiraProject(projectsJson.getJSONObject(i)));
        }

        for(JiraProject jiraProject : jiraProjects){
            System.out.println(jiraProject);
        }


    }
}

