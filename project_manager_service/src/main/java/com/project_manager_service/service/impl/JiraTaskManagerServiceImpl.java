package com.project_manager_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.project_manager_service.form.CreateJiraIssueRequest;
import com.project_manager_service.form.UpdateJiraIssueRequest;
import com.project_manager_service.service.JiraTaskManagerService;
import com.util.JiraUtils;
import org.springframework.stereotype.Service;

@Service
public class JiraTaskManagerServiceImpl implements JiraTaskManagerService {

    @Override
    public String createIssue(CreateJiraIssueRequest createJiraIssueRequest) throws UnirestException, JsonProcessingException {
        return JiraUtils.createIssue(createJiraIssueRequest.projectKey(),
                createJiraIssueRequest.summary(),
                createJiraIssueRequest.description(),
                createJiraIssueRequest.issueTypeKey(),
                createJiraIssueRequest.assignee(),
                createJiraIssueRequest.labels());
    }

    @Override
    public String updateIssue(UpdateJiraIssueRequest updateJiraIssueRequest) throws UnirestException, JsonProcessingException {
        return JiraUtils.updateIssue(updateJiraIssueRequest.issueTypeKey(),
                updateJiraIssueRequest.projectKey(),
                updateJiraIssueRequest.summary(),
                updateJiraIssueRequest.description(),
                updateJiraIssueRequest.issueTypeKey(),
                updateJiraIssueRequest.assignee(),
                updateJiraIssueRequest.labels()
                );
    }

    @Override
    public String deleteIssue(String issueId) throws UnirestException, JsonProcessingException {
        return JiraUtils.deleteIssue(issueId);
    }

    @Override
    public String getProjectIssueTypes(String projectKey) throws UnirestException, JsonProcessingException {
        return JiraUtils.getProjectIssueTypes(projectKey);
    }
}
