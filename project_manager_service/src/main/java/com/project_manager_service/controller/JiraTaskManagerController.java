package com.project_manager_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.project_manager_service.form.CreateJiraIssueRequest;
import com.project_manager_service.form.UpdateJiraIssueRequest;
import com.project_manager_service.service.JiraTaskManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/project/jira/issue",produces = "application/json")
@RequiredArgsConstructor
public class JiraTaskManagerController {


    private final JiraTaskManagerService jiraTaskManagerService;

    @PostMapping(value = "/create")
    public String createProjectIssue(@Valid @RequestBody CreateJiraIssueRequest createJiraIssueRequest) throws JsonProcessingException, UnirestException {
        return jiraTaskManagerService.createIssue(createJiraIssueRequest);
    }

    @PutMapping(value = "/update")
    public String updateProjectIssue(@Valid @RequestBody UpdateJiraIssueRequest updateJiraIssueRequest) throws JsonProcessingException, UnirestException {
        return jiraTaskManagerService.updateIssue(updateJiraIssueRequest);
    }

    @PutMapping(value = "/type")
    public String getProjectIssueTypes(@RequestParam("projectKey")String projectKey) throws JsonProcessingException, UnirestException {
        return jiraTaskManagerService.getProjectIssueTypes(projectKey);
    }


    @DeleteMapping(value = "/{issueId}")
    public String deleteProjectIssue(@PathVariable("issueId")String taskId) throws JsonProcessingException, UnirestException {
        return jiraTaskManagerService.deleteIssue(taskId);
    }

}
