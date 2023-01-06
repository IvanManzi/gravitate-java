package com.project_manager_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.project_manager_service.form.CreateJiraIssueRequest;
import com.project_manager_service.form.UpdateJiraIssueRequest;

public interface JiraTaskManagerService {

    String createIssue(CreateJiraIssueRequest createJiraIssueRequest) throws UnirestException, JsonProcessingException;

    String updateIssue(UpdateJiraIssueRequest updateJiraIssueRequest) throws UnirestException, JsonProcessingException;

    String deleteIssue(String issueId) throws UnirestException, JsonProcessingException;

    String getProjectIssueTypes(String projectKey) throws UnirestException, JsonProcessingException;
}
