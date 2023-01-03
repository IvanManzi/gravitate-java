package com.project_manager_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.project_manager_service.dao.ProjectDao;
import com.project_manager_service.service.JiraProjectManagerService;
import com.util.JiraUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
@RequiredArgsConstructor
public class JiraProjectManagerServiceImpl implements JiraProjectManagerService {

    private final ProjectDao projectDao;


    @Override
    public String getAllProjects(Long userId,String userLevel,Integer phase) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        List<Map> projects = projectDao.getAllProjects(userId,userLevel,phase);
        if(projects.isEmpty()){
            return null;
        }
        return JiraUtils.getAllProjects1(projects);
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