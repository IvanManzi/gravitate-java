package com.project_manager_service.service.impl;

import com.model.ProjectVO;
import com.project_manager_service.dao.ProjectDao;
import com.project_manager_service.dao.UserProjectDao;
import com.project_manager_service.service.ProjectCRUDService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectCRUDServiceImpl implements ProjectCRUDService {

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
        List<Map> projects = projectDao.getAllProjects();
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
    public ResponseEntity deleteProject(Long projectId) {
        int result = projectDao.deleteProject(projectId);
        if(result > 0){
            return APIResponse.resultSuccess("Project successfully deleted. ");
        }else{
            return APIResponse.resultFail();
        }
    }
}
