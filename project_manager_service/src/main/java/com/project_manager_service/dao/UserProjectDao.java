package com.project_manager_service.dao;

import com.model.ProjectVO;
import com.model.UserProjectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserProjectDao {

    int assignUserToProjects2(@Param("userId") Long userId, @Param("projects") List<Long> projectId);
    int markAsFavorite(UserProjectVO userProjectVO);
    List<Map> getAllProjectsAndAssignedUsers();
    List<Map> getAllProjectsBillingInformation(@Param("userId") Long userId,@Param("from") Date from,@Param("to") Date to);
    int checkIfUserIsAssignedToProject(@Param("userId") Long userId,@Param("projectId") Long projectId);
    List<Long> getGravitateUserProjects(Long userId);
    int removeAllAssignedProjects(Long userId);
    int removeAllAssignedProjects2(@Param("userId") Long userId,@Param("projects") List<Long> projects);
    int removeUserOnProject(@Param("userId") Long userId,@Param("projectId") Long projectId);
}
