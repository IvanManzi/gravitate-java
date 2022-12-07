package com.content_manager_service.service.impl;

import com.content_manager_service.dao.ProjectDao;
import com.content_manager_service.dao.TaskReportDao;
import com.content_manager_service.service.TaskReportManagerService;
import com.model.ProjectVO;
import com.model.TaskReportVO;
import com.util.APIResponse;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class TaskReportManagerServiceImpl implements TaskReportManagerService {

    private final TaskReportDao taskReportDao;
    private final ProjectDao projectDao;

    @Override
    public ResponseEntity<APIResponse> createTaskReport(TaskReportVO taskReportVO) {
        //check if project jira id exists
        ProjectVO project = projectDao.getProjectByJiraId(taskReportVO.getJiraProjectId());
        if(ValidationUtil.isNullObject(project)){
            return APIResponse.resultFail("Project with given jira id doesn't exists. ");
        }
        taskReportVO.setProjectId(project.getProjectId());
        int result = taskReportDao.createTaskReport(taskReportVO);
        if(result > 0){
            return APIResponse.resultSuccess();
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> getUserTaskReports(Long userId) {
        List<TaskReportVO> userTaskReports = taskReportDao.getUserTaskReports(userId);
        if(userTaskReports.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_REPORTS",userTaskReports);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> getAllUsersTaskReports() {
        List<Map> taskReports = taskReportDao.getAllUserTaskReports();
        if(taskReports.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("TASK_REPORTS",taskReports);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> updateTaskReport(TaskReportVO taskReportVO) {
        int result = taskReportDao.updateTaskReport(taskReportVO);
        if(result > 0){
            return APIResponse.resultSuccess("Task report successfully updated.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> deleteTaskReport(Long reportId) {
        int result = taskReportDao.deleteTaskReport(reportId);
        if(result > 0){
            return APIResponse.resultSuccess("Task report successfully deleted.");
        }
        return APIResponse.resultFail();
    }
}
