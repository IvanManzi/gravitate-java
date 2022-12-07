package com.content_manager_service.dao;

import com.model.ProjectIncentiveVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectIncentiveDao {

    int createProjectIncentive(ProjectIncentiveVO projectIncentiveVO);

    List<ProjectIncentiveVO> getUserProjectIncentives(@Param("userId") Long userId,@Param("projectId") Long projectId);
}
