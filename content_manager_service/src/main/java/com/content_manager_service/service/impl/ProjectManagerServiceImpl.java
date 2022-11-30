package com.content_manager_service.service.impl;

import com.content_manager_service.dao.ProjectDao;
import com.content_manager_service.dao.UserProjectDao;
import com.content_manager_service.service.ProjectManagerService;
import com.model.ProjectVO;
import com.util.APIResponse;
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
    public ResponseEntity createProject(ProjectVO projectVO) {
        int result = projectDao.createProject(projectVO);
        if(result > 0){
            return APIResponse.resultSuccess("Project successfully created.");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllProjects() {
        List<ProjectVO> projects = projectDao.getAllProjects();
        if(projects.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("PROJECTS",projects);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity updateProject(ProjectVO projectVO) {
        int result = projectDao.updateProject(projectVO);
        if(result > 0){
            return APIResponse.resultSuccess("Project successfully updated");
        }else{
            return APIResponse.resultFail();
        }
    }

    @Override
    public boolean assignUserToProject(Long userId, List<Long> projects) {
        List<Long> userProjects = new ArrayList<>();
        //check if user isn't already assigned to these projects
        for(Long projectId : projects){
            int status = userProjectDao.checkIfUserIsAssignedToProject(userId,projectId);
            if(status != 1){
                userProjects.add(projectId);
            }
        }
        if(userProjects.isEmpty()){
            return true;// User is already assigned to projects
        }
        int result = userProjectDao.createUserProject(userId,userProjects);
        if(result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity getUsersAssignedToProjects() {
        List<Map> projects = userProjectDao.getAllGravitateUsersAssignedProjects();
        if(projects.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("PROJECTS",projects);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity getGravitateUserProjects(Long userId) {
        List<ProjectVO> userProjects = userProjectDao.getGravitateUserProjects(userId);
        if(userProjects.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_PROJECTS",userProjects);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity deleteProject(Long projectId) {
        int result = projectDao.deleteProject(projectId);
        if(result > 0){
            return APIResponse.resultSuccess("Project successfully deleted. ");
        }else{
            return APIResponse.resultFail();
        }
    }
}
