package com.content_manager_service.dao;

import com.model.ProjectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDao {

    int createProject(ProjectVO projectVO);

    List<ProjectVO> getProjectByAdminId(Long adminId);

    int updateProject(ProjectVO projectVO);

    int deleteProject(Long projectId);
}
