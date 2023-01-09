package com.project_manager_service.service.impl;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.ProjectVO;
import com.project_manager_service.dao.ProjectDao;
import com.project_manager_service.dao.ProjectIncentiveDao;
import com.project_manager_service.dao.TaskReportDao;
import com.project_manager_service.dao.UserProjectDao;
import com.project_manager_service.service.GravitateUserProjectService;
import com.util.APIResponse;
import com.util.JiraUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GravitateUserProjectServiceImpl implements GravitateUserProjectService {
    private final UserProjectDao userProjectDao;

    private final TaskReportDao taskReportDao;

    private final ProjectDao projectDao;

    private final ProjectIncentiveDao projectIncentiveDao;

    @Override
    public boolean assignUserToProject(String jiraAccountId ,Long userId, List<Long> projects) throws UnirestException {
        List<String> jiraProjects = new ArrayList<>();
        //check if user isn't already assigned to these projects
        for(Long projectId : projects){
            int status = userProjectDao.checkIfUserIsAssignedToProject(userId,projectId);
            if(status == 1){
                projects.remove(projectId);
            }else{
                jiraProjects.add(projectDao.getProjectById(projectId).getJiraProjectKey());
            }
        }
        if(projects.isEmpty()){
            return true;// User is already assigned to projects
        }
        log.info("{}", projects);
        log.info("{}",jiraProjects);

        int result = userProjectDao.assignUserToProjects(userId,projects);
        if(result > 0) {
            for(String project : jiraProjects){
                JiraUtils.assignProjectToUser(project,jiraAccountId);
            }
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity getAllAssignedProjectBillingInformation() {
        List<Map> projectsBillingInfo = userProjectDao.getAllProjectsAndAssignedUsers();
        if(projectsBillingInfo.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        for (Map project : projectsBillingInfo){
            project.put("TASK_REPORTS",taskReportDao.getUserTaskReports((Long) project.get("user_id"),(Long) project.get("project_id")));
            project.put("PROJECT_INCENTIVES",projectIncentiveDao.getUserProjectIncentives((Long) project.get("user_id"),(Long) project.get("project_id")));
        }
        Map<String,Object> data = new HashMap<>();
        data.put("BILLING_INFO",projectsBillingInfo);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity getUserBillingInformation(Long userId, Date from, Date to) {
        List<Map> userBillingInfo = userProjectDao.getAllProjectsBillingInformation(userId,from,to);
        if(userBillingInfo.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_BILLING_INFO",userBillingInfo);
        return APIResponse.resultSuccess(data);
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
}
