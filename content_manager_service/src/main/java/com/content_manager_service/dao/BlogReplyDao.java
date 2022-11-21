package com.content_manager_service.dao;

import com.model.BlogReplyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogReplyDao {

    int createBlogReply(BlogReplyVO blogReplyVO);
}
