package com.project_manager_service.dao;

import com.model.TaskReportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskReportDao {
    int createTaskReport(TaskReportVO taskReportVO);

    int isTaskReported(TaskReportVO taskReportVO);
    List<TaskReportVO> getUserTaskReports(@Param("userId") Long userId,@Param("projectId") Long projectId);
    int updateTaskReport(TaskReportVO taskReportVO);
    List<Map> getAllTaskReports(@Param("userId")Long userId,
                                @Param("userLevel") String userLevel,
                                @Param("isArchived") boolean isArchived);
    int deleteTaskReport(Long reportId);
    int markMonthlyTasksAsPaid(@Param("userId")Long userId,
                               @Param("month") Integer month,
                               @Param("year") Integer year,
                               @Param("projectId") Long projectId);
    int checkIfMonthlyTaskReportsArePaid(@Param("projectId") Long projectId,
                                         @Param("userId") Long userId,
                                         @Param("month") Integer month);

    int unDomarkMonthlyTasksAsPaid(@Param("userId") Long userId,
                                   @Param("month") Integer month,
                                   @Param("year") Integer year,
                                   @Param("projectId") Long projectId);
}
