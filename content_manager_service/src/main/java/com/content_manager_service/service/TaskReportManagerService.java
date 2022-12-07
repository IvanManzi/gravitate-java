package com.content_manager_service.service;

import com.model.TaskReportVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface TaskReportManagerService {

    ResponseEntity<APIResponse> createTaskReport(TaskReportVO taskReportVO);

    ResponseEntity<APIResponse> getUserTaskReports(Long userId);

    ResponseEntity<APIResponse> getAllUsersTaskReports();

    ResponseEntity<APIResponse> updateTaskReport(TaskReportVO taskReportVO);

    ResponseEntity<APIResponse> deleteTaskReport(Long reportId);


}
