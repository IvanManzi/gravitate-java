package com.project_manager_service.service;

import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface JiraProjectManagerService {

    ResponseEntity<APIResponse> getAllProjects();

    ResponseEntity<APIResponse> getSingleProject(String url);



}
