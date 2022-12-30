package com.project_manager_service.dao;

import com.model.TaskReportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskReportDao {
    int createTaskReport(TaskReportVO taskReportVO);
    List<TaskReportVO> getUserTaskReports(@Param("userId") Long userId,@Param("projectId") Long projectId);
    int updateTaskReport(TaskReportVO taskReportVO);
    List<Map> getAllTaskReports(Long userId,String userLevel);
    int deleteTaskReport(Long reportId);
}
