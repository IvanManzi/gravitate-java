package com.project_manager_service.service.impl;

import com.model.ProjectIncentiveVO;
import com.project_manager_service.dao.ProjectIncentiveDao;
import com.project_manager_service.dao.TaskReportDao;
import com.project_manager_service.service.ProjectIncentiveManagerService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectIncentiveManagerServiceImpl implements ProjectIncentiveManagerService {

    private final ProjectIncentiveDao projectIncentiveDao;

    private final TaskReportDao taskReportDao;

    @Override
    public ResponseEntity<APIResponse> createProjectIncentive(ProjectIncentiveVO projectIncentiveVO) {
        //check if user hasn't received an incentive on the project
        int check = projectIncentiveDao.checkIfIncentiveExistsInCurrentMonth(projectIncentiveVO.getProjectId(),projectIncentiveVO.getUserId(), projectIncentiveVO.getMonth());
        int check2 = taskReportDao.checkIfMonthlyTaskReportsArePaid(projectIncentiveVO.getProjectId(),projectIncentiveVO.getUserId(),projectIncentiveVO.getMonth());

        if(check == 1){
            return APIResponse.resultFail("Project incentive already awarded.");
        }

        if(check2 == 1){
            return APIResponse.resultFail("Tasks for selected month were marked as paid.");
        }
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
