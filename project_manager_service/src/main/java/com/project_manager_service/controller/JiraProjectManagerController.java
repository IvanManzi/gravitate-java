package com.project_manager_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.project_manager_service.service.JiraProjectManagerService;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/project/jira",produces = "application/json")
@RequiredArgsConstructor
public class JiraProjectManagerController {


    private final JiraProjectManagerService jiraProjectManagerService;



    @GetMapping(value = "/all")
    public String getAllJiraProjects(HttpServletRequest request,@RequestParam(value = "phase",required = false)Integer phase) throws UnirestException, ExecutionException, InterruptedException, IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        String role = JwtUtils.getUserRoleFromJwtToken(token);
        return jiraProjectManagerService.getAllProjects(Long.valueOf(userId),role,phase);
    }

    @GetMapping(value = "/assignee")
    public String getAssignedUsersTasks(@RequestParam("projectKey") String projectKey) throws UnirestException, ExecutionException, InterruptedException, JsonProcessingException {
        return jiraProjectManagerService.getProjectTeamWithAssignedTasks(projectKey);
    }

    @GetMapping(value = "/search")
    public String getUserAssignedTasks(@RequestParam("projectKey")String key,
                                       @RequestParam("accountId") String accountId,@RequestParam(value = "status",required = false)String status) throws ExecutionException, InterruptedException, JsonProcessingException {
        return jiraProjectManagerService.getUserAssignedTasks(key,accountId,status);
    }

}