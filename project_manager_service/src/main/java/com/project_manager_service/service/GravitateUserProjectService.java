package com.project_manager_service.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GravitateUserProjectService {

    boolean assignUserToProject(Long userId, List<Long> projects);
    ResponseEntity getUsersAssignedToProjects();
    ResponseEntity getGravitateUserProjects(Long userId);
}
