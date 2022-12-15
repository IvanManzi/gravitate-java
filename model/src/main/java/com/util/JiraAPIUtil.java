package com.util;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;

public class JiraAPIUtil {

    private static final String jiraUsername = "nostalgie@hviewtech.com";
    private static final String jiraToken = "NH7ccfsEdZFsMCdRqWth928C";
    private static final String jiraPassword = "Mftpnj123alva!";
    private static final String jiraUrl = "https://highviewtech.atlassian.net/";


    public static JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), jiraUsername, jiraPassword);
    }

    public static URI getJiraUri() {
        return URI.create(jiraUrl);
    }

    public static void main(String[] args) {
        getJiraRestClient();
    }

}