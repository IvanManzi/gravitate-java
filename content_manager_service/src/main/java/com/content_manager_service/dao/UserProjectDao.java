package com.content_manager_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserProjectDao {

    int createUserProject(@Param("userId") Long userId,@Param("projects") List<Long> projectId);

    List<Map> getAllProjects();
}
