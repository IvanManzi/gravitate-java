package com.content_manager_service.dao;

import com.model.BlogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {

    int createBlog(BlogVO blogVO);

    List<Map> getTeamMembersBlogs(Long managerId);

    int updateBlog(BlogVO blogVO);

    int deleteBlog(Long blogId);
}
