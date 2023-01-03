package com.project_manager_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.util.APIResponse;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public interface JiraProjectManagerService {

    String getAllProjects(Long userId,String userLevel,Integer phase) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException;

    String getProjectTeamWithAssignedTasks(String projectId) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException;

    String getUserAssignedTasks(String projectKey,String accountKey,String status) throws ExecutionException, InterruptedException, JsonProcessingException;
}
