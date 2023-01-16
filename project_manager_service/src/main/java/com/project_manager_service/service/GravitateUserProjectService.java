package com.project_manager_service.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface GravitateUserProjectService {

    boolean assignUserToProject(String jiraAccountId,Long userId, List<Long> projects) throws UnirestException;
    ResponseEntity getAllAssignedProjectBillingInformation();
    ResponseEntity getUserBillingInformation(Long userId, Date from, Date to);
}
