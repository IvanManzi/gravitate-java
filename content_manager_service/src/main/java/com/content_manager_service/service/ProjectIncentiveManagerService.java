package com.content_manager_service.service;

import com.model.ProjectIncentiveVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface ProjectIncentiveManagerService {

    ResponseEntity<APIResponse> createProjectIncentive(ProjectIncentiveVO projectIncentiveVO);
    ResponseEntity<APIResponse> getUserProjectIncentives(Long userId, Long projectId);
}
