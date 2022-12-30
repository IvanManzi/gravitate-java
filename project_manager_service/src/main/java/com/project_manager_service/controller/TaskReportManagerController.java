package com.project_manager_service.controller;

import com.model.TaskReportVO;
import com.project_manager_service.form.CreateTaskReportRequest;
import com.project_manager_service.form.UpdateTaskReportRequest;
import com.project_manager_service.service.TaskReportManagerService;
import com.util.APIResponse;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "api/v1/project/task",produces = "application/json")
@RequiredArgsConstructor
public class TaskReportManagerController {

    private final TaskReportManagerService taskReportManagerService;

    @PostMapping(value = "/create")
    public ResponseEntity<APIResponse> createTaskReport(@Valid @RequestBody CreateTaskReportRequest createTaskReportRequest,
                                                        HttpServletRequest request) throws IOException {
        TaskReportVO taskReportVO = new TaskReportVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        taskReportVO.setUserId(Long.valueOf(userId));
        taskReportVO.setJiraProjectId(createTaskReportRequest.projectId());
        taskReportVO.setJiraTaskId(createTaskReportRequest.taskId());
        taskReportVO.setTaskName(createTaskReportRequest.name());
        taskReportVO.setHoursSpent(createTaskReportRequest.hours());
        taskReportVO.setStartDate(createTaskReportRequest.date());
        return taskReportManagerService.createTaskReport(taskReportVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<APIResponse> getAllGravitateUserTaskReports(HttpServletRequest request) throws IOException {
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        String role = JwtUtils.getUserRoleFromJwtToken(token);
        return taskReportManagerService.getAllTaskReports(Long.valueOf(userId),role);
    }

    @PutMapping(value = "/")
    public ResponseEntity<APIResponse> updateTaskReport(@Valid @RequestBody UpdateTaskReportRequest
                                                                    updateTaskReportRequest){

        TaskReportVO taskReportVO = new TaskReportVO();
        taskReportVO.setTaskReportId(updateTaskReportRequest.reportId());
        taskReportVO.setJiraProjectId(updateTaskReportRequest.projectId());
        taskReportVO.setJiraTaskId(updateTaskReportRequest.taskId());
        return taskReportManagerService.updateTaskReport(taskReportVO);
    }

    @DeleteMapping(value = "/{reportId}")
    public ResponseEntity<APIResponse> deleteTaskReport(@PathVariable("reportId") Long reportId){
        return taskReportManagerService.deleteTaskReport(reportId);
    }
}
