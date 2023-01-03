package com.project_manager_service.service.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.ProjectVO;
import com.project_manager_service.dao.ProjectDao;
import com.project_manager_service.service.JiraProjectManagerService;
import com.util.APIResponse;
import com.util.JiraUtils;
import com.util.MyJiraClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class JiraProjectManagerServiceImpl implements JiraProjectManagerService {

    private final ProjectDao projectDao;


    @Override
    public String getAllProjects(Long userId,String userLevel) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        List<Map> projects = projectDao.getAllProjects(userId,userLevel,null);
        return JiraUtils.getAllProjects(projects);
    }

    @Override
    public String getProjectTeamWithAssignedTasks(String projectId) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        return JiraUtils.getAssignedUsersWithTasks1(projectId);
    }

    @Override
    public String getUserAssignedTasks(String projectKey,String accountKey, String status) throws ExecutionException, InterruptedException, JsonProcessingException {
        return JiraUtils.getUserProjectTasks1(projectKey,accountKey,status);
    }


}