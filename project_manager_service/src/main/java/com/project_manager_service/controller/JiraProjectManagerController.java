package com.project_manager_service.controller;

import com.project_manager_service.service.JiraProjectManagerService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/project/jira",produces = "application/json")
@RequiredArgsConstructor
public class JiraProjectManagerController {


    private final JiraProjectManagerService jiraProjectManagerService;


    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllJiraProjects(){
        return jiraProjectManagerService.getAllProjects();
    }

}
