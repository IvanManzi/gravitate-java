package com.project_manager_service.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public interface JiraProjectManagerService {

    ResponseEntity<APIResponse> getAllProjects() throws UnirestException, ExecutionException, InterruptedException;

    ResponseEntity<APIResponse> getProjectTeamWithAssignedTasks(String projectId) throws UnirestException, ExecutionException, InterruptedException;
}
