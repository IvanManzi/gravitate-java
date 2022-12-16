package com.project_manager_service.service;

import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface GravitateUserProjectService {

    boolean assignUserToProject(Long userId, List<Long> projects);
    ResponseEntity getAllAssignedProjectBillingInformation();
    ResponseEntity getUserBillingInformation(Long userId, Date from, Date to);
    ResponseEntity getGravitateUserProjects(Long userId);
}
