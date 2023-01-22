package com.project_manager_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.ProjectVO;
import com.model.UserProjectVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface ProjectManagerService {

    ResponseEntity<APIResponse> createProject(ProjectVO projectVO) throws UnirestException, JsonProcessingException;

    ResponseEntity<APIResponse> getAllProjects(Long userId, String role,Integer phase);

    ResponseEntity<APIResponse> updateProject(ProjectVO projectVO) throws UnirestException, JsonProcessingException;

    ResponseEntity<APIResponse> markProjectAsFavorite(UserProjectVO userProjectVO);

    ResponseEntity<APIResponse> updateProjectPhase(ProjectVO projectVO);

    ResponseEntity<APIResponse> updateProjectStatus(ProjectVO projectVO);

    ResponseEntity<APIResponse> deleteProject(String projectKey) throws UnirestException, JsonProcessingException;
}
