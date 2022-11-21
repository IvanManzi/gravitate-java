package com.content_manager_service.service;

import com.model.BlogReplyVO;
import com.model.BlogVO;
import org.springframework.http.ResponseEntity;

public interface GravitateBlogManagerService {

    ResponseEntity createBlog(BlogVO blogVO);

    ResponseEntity getTeamMembersBlogs(Long managerId);

    ResponseEntity createBlogComment(BlogReplyVO blogReplyVO);

    ResponseEntity updateBlog(BlogVO blogVO);

    ResponseEntity deleteBlog(Long blogId);
}
