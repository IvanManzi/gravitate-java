package com.project_manager_service.dao;

import com.model.ProjectIncentiveVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectIncentiveDao {

    int createProjectIncentive(ProjectIncentiveVO projectIncentiveVO);

    List<ProjectIncentiveVO> getUserProjectIncentives(@Param("userId") Long userId,@Param("projectId") Long projectId);

    int checkIfIncentiveExistsInCurrentMonth(@Param("projectId") Long projectId,
                                             @Param("userId") Long userId,
                                             @Param("month") Integer month);

    int markMonthlyProjectIncentiveAsPaid(@Param("userId") Long userId ,
                                          @Param("month") Integer month,
                                          @Param("year") Integer year,
                                          @Param("projectId") Long projectId);

    int unDomarkMonthlyProjectIncentiveAsPaid(@Param("userId") Long userId ,
                                              @Param("month") Integer month,
                                              @Param("year") Integer year,
                                              @Param("projectId") Long projectId);
}
