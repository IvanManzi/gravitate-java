package com.project_manager_service.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.project_manager_service.service.JiraProjectManagerService;
import com.util.APIResponse;
import com.util.JiraUtils;
import com.util.MyJiraClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class JiraProjectManagerServiceImpl implements JiraProjectManagerService {


    @Override
    public ResponseEntity<APIResponse> getAllProjects() throws UnirestException, ExecutionException, InterruptedException {
        Map<String,Object> data = new HashMap<>();
        data.put("DATA", (Object) JiraUtils.getAllProjects());
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> getProjectTeamWithAssignedTasks(String projectId) throws UnirestException, ExecutionException, InterruptedException {
        Map<String,Object> data = new HashMap<>();
        data.put("DATA", (Object) JiraUtils.getAssignedUsersWithTasks1(projectId));
        return APIResponse.resultSuccess(data);
    }


}