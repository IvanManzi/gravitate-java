package com.project_manager_service.dao;

import com.model.ProjectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectDao {

    int createProject(ProjectVO projectVO);
    ProjectVO getProjectByJiraId(String jiraId);
    int checkIfProjectNameIsUnique(ProjectVO projectVO);
    ProjectVO getProjectById(Long projectId);
    List<Map> getAllProjects(@Param("userId")Long userId,
                             @Param("userLevel")String role,@Param("phase")Integer phase);
    int updateProject(ProjectVO projectVO);
    int deleteProject(String jiraProjectKey);
    int updateProjectPhase(ProjectVO projectVO);
    int updateProjectStatus(ProjectVO projectVO);
}
