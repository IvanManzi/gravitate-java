package com.project_manager_service.service;

import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface JiraProjectTaskService {

    ResponseEntity<APIResponse> getProjectsAndAssignedUsers();
}
