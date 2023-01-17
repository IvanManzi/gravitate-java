package com.project_manager_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.ProjectVO;
import com.project_manager_service.dao.ProjectDao;
import com.project_manager_service.dao.UserProjectDao;
import com.project_manager_service.service.ProjectManagerService;
import com.util.APIResponse;
import com.util.JiraUtils;
import com.util.VerifyCodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectManagerServiceImpl implements ProjectManagerService {

    private final ProjectDao projectDao;

    private final UserProjectDao userProjectDao;


    @Override
    public ResponseEntity createProject(ProjectVO projectVO) throws UnirestException, JsonProcessingException {
        projectVO.setJiraProjectKey(VerifyCodeUtils.generateVerifyCode(4,"ABCDEFGHIJKLKMNOPQRSTUVWXYZ"));
        int result = projectDao.createProject(projectVO);
        if(result > 0){
            JiraUtils.createJiraProject(projectVO.getProjectName(),
                    projectVO.getJiraProjectKey(),
                    projectVO.getProjectDescription(),
                    projectVO.getLeadJiraAccountId());
            return APIResponse.resultSuccess("Project successfully created.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllProjects(Long userId,String role,Integer phase) {
        List<Map> projects = projectDao.getAllProjects(userId,role,phase);
        if(projects.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("PROJECTS",projects);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateProject(ProjectVO projectVO) throws UnirestException, JsonProcessingException {
        int result = projectDao.updateProject(projectVO);
        if(result > 0){
            JiraUtils.updateJiraProject(projectVO.getJiraProjectKey(),
                    projectVO.getProjectName(),
                    projectVO.getProjectDescription(),projectVO.getLeadJiraAccountId());
            return APIResponse.resultSuccess("Project successfully updated");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity<APIResponse> markProjectAsFavorite(Long projectId) {
        int result = projectDao.markAsFavorite(projectId);
        if(result > 0){
            return APIResponse.resultSuccess("Project successfully marked as favorite!");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> updateProjectPhase(ProjectVO projectVO) {
        int result = projectDao.updateProjectPhase(projectVO);
        if(result > 0){
            return APIResponse.resultSuccess("Project phase successfully updated.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> updateProjectStatus(ProjectVO projectVO) {
        int result = projectDao.updateProjectStatus(projectVO);
        if(result > 0){
            return APIResponse.resultSuccess("Project status successfully updated.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity deleteProject(String projectKey) throws UnirestException, JsonProcessingException {
        int result = projectDao.deleteProject(projectKey);
        if(result > 0){
            JiraUtils.deleteJiraProject(projectKey);
            return APIResponse.resultSuccess("Project successfully deleted. ");
        }else{
            return APIResponse.resultFail();
        }
    }
}
