package com.project_manager_service.dao;

import com.model.TaskReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskReportDao {
    int createTaskReport(TaskReportVO taskReportVO);
    List<TaskReportVO> getUserTaskReports(Long userId);
    int updateTaskReport(TaskReportVO taskReportVO);
    List<Map> getAllUserTaskReports();
    int deleteTaskReport(Long reportId);
}
