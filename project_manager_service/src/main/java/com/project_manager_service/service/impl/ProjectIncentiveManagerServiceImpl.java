package com.project_manager_service.service.impl;

import com.model.ProjectIncentiveVO;
import com.project_manager_service.dao.ProjectIncentiveDao;
import com.project_manager_service.service.ProjectIncentiveManagerService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProjectIncentiveManagerServiceImpl implements ProjectIncentiveManagerService {

    private final ProjectIncentiveDao projectIncentiveDao;

    @Override
    public ResponseEntity<APIResponse> createProjectIncentive(ProjectIncentiveVO projectIncentiveVO) {
        int result = projectIncentiveDao.createProjectIncentive(projectIncentiveVO);
        if(result > 0){
            return APIResponse.resultSuccess("Project Incentive successfully created. ");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> getUserProjectIncentives(Long userId, Long projectId) {
        List<ProjectIncentiveVO> userProjectIncentives = projectIncentiveDao.getUserProjectIncentives(userId,projectId);
        if(userProjectIncentives.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("USER_PROJECT_INCENTIVES", data);
        return APIResponse.resultSuccess(data);
    }
}
