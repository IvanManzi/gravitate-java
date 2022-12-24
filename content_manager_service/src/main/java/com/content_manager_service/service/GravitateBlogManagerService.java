package com.content_manager_service.service;


import com.model.BlogReplyVO;
import com.model.BlogVO;
import org.springframework.http.ResponseEntity;

public interface GravitateBlogManagerService {

    ResponseEntity createBlog(BlogVO blogVO);

    ResponseEntity createBlogReply(BlogReplyVO blogReplyVO);

    ResponseEntity getAllBlogs(String search, String topic);

    ResponseEntity getUserBlogsByQuarter(Long userId, Integer quarter);

    ResponseEntity updateBlog(BlogVO blogVO);

    ResponseEntity deleteBlog(Long blogId);
}
