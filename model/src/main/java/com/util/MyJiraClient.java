package com.util;


import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MyJiraClient {

    private static String username = "nostalgie@hviewtech.com";
    private static String password = "NH7ccfsEdZFsMCdRqWth928C";
    private static String jiraUrl = "https://highviewtech.atlassian.net/";
    private static JiraRestClient restClient = getJiraRestClient();

    public MyJiraClient(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    public static MyJiraClient instantiateClient(){
        MyJiraClient myJiraClient = new MyJiraClient(username, password, jiraUrl);
        return myJiraClient;
    }

    public static void main(String[] args) throws IOException {

        //MyJiraClient myJiraClient = new MyJiraClient("nostalgie@hviewtech.com", "NH7ccfsEdZFsMCdRqWth928C", "https://highviewtech.atlassian.net/");
        MyJiraClient myJiraClient = instantiateClient();
        //System.out.println(myJiraClient.getAllProjects());
        System.out.println(myJiraClient.getSingleProject(URI.create("https://highviewtech.atlassian.net/rest/api/2/project/10001")));
        /*final String issueKey = myJiraClient.createIssue("ABCD", 1L, "Issue created from JRJC");
        myJiraClient.updateIssueDescription(issueKey, "This is description from my Jira Client");
        Issue issue = myJiraClient.getIssue(issueKey);
        System.out.println(issue.getDescription());

        myJiraClient.voteForAnIssue(issue);

        System.out.println(myJiraClient.getTotalVotesCount(issueKey));

        myJiraClient.addComment(issue, "This is comment from my Jira Client");

        List<Comment> comments = myJiraClient.getAllComments(issueKey);
        comments.forEach(c -> System.out.println(c.getBody()));

        myJiraClient.deleteIssue(issueKey, true);*/

        myJiraClient.restClient.close();
    }

    public static String createIssue(String projectKey, Long issueType, String issueSummary) {

        IssueRestClient issueClient = restClient.getIssueClient();

        IssueInput newIssue = new IssueInputBuilder(projectKey, issueType, issueSummary).build();

        return issueClient.createIssue(newIssue).claim().getKey();
    }

    public static Issue getIssue(String issueKey) {
        return restClient.getIssueClient().getIssue(issueKey).claim();
    }

    public static void voteForAnIssue(Issue issue) {
        restClient.getIssueClient().vote(issue.getVotesUri()).claim();
    }

    public static int getTotalVotesCount(String issueKey) {
        BasicVotes votes = getIssue(issueKey).getVotes();
        return votes == null ? 0 : votes.getVotes();
    }

    public static void addComment(Issue issue, String commentBody) {
        restClient.getIssueClient().addComment(issue.getCommentsUri(), Comment.valueOf(commentBody));
    }

    public List<Comment> getAllComments(String issueKey) {
        return StreamSupport.stream(getIssue(issueKey).getComments().spliterator(), false)
                .collect(Collectors.toList());
    }

    public static void updateIssueDescription(String issueKey, String newDescription) {
        IssueInput input = new IssueInputBuilder().setDescription(newDescription).build();
        restClient.getIssueClient().updateIssue(issueKey, input).claim();
    }

    public static void deleteIssue(String issueKey, boolean deleteSubtasks) {
        restClient.getIssueClient().deleteIssue(issueKey, deleteSubtasks).claim();
    }

    public static JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), username, password);
    }

    public static Iterable<BasicProject> getAllProjects(){
        return restClient.getProjectClient().getAllProjects().claim();
    }

    public static Project getSingleProject(URI key){
        return restClient.getProjectClient().getProject(key).claim();
    }

    public static URI getJiraUri() {
        return URI.create(jiraUrl);
    }


}
