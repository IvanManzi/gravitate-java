package com.project_manager_service.service.impl;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.model.ProjectIncentiveVO;
import com.model.ProjectVO;
import com.model.TaskReportVO;
import com.project_manager_service.dao.ProjectDao;
import com.project_manager_service.dao.ProjectIncentiveDao;
import com.project_manager_service.dao.TaskReportDao;
import com.project_manager_service.dao.UserProjectDao;
import com.project_manager_service.service.GravitateUserProjectService;
import com.util.APIResponse;
import com.util.JiraUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
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
        List<Long> currentlyAssignedProjects = userProjectDao.getGravitateUserProjects(userId);
        System.out.println("currently assigned "+ currentlyAssignedProjects);
        System.out.println("obj "+ projects);
        if(projects.isEmpty()){
            //remove all assigned projects
            userProjectDao.removeAllAssignedProjects(userId);
            for (Long projectId : currentlyAssignedProjects) {
                JiraUtils.removeActorFromProject(jiraAccountId,projectDao.getProjectById(projectId).getJiraProjectKey());
            }
            return true;
        }

        List<Long> newProjects = new ArrayList<Long>(projects);
        List<Long> removedProjects = new ArrayList<Long>(currentlyAssignedProjects);

        newProjects.removeAll(currentlyAssignedProjects);
        removedProjects.removeAll(projects);

        //System.out.println("New Projects: " + newProjects);
        //System.out.println("Removed Projects: " + removedProjects);

        if(newProjects.size() > 0){
            int result = userProjectDao.assignUserToProjects2(userId,projects);
            //check if removedProjects array is empty
            if(removedProjects.size() > 0){
                //remove miss-allocated projects
                userProjectDao.removeAllAssignedProjects2(userId,removedProjects);
            }
            if(result > 0) {
                for (Long projectId : newProjects) {
                    JiraUtils.assignProjectToUser(projectDao.getProjectById(projectId).getJiraProjectKey(), jiraAccountId);
                }
            }
        }
        return true;
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
        //loop through the result to add paid status for each monthly tasks
        for(Map billingInfo : userBillingInfo){
            List<Map> taskReports = (List<Map>) billingInfo.get("tasks");
            if(!taskReports.isEmpty()){
                if(taskReports.get(0).get("is_paid").equals(false)){
                    billingInfo.put("isPaid",false);
                }else{
                    billingInfo.put("isPaid",true);
                }
            }else{
                billingInfo.put("isPaid",false);
            }
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_BILLING_INFO",userBillingInfo);
        return APIResponse.resultSuccess(data);
    }
}
