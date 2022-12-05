package com.content_manager_service.controller;

import com.content_manager_service.form.CreateDiscussionForumRequest;
import com.content_manager_service.form.UpdateDiscussionForumRequest;
import com.content_manager_service.service.DiscussionForumManagerService;
import com.model.DiscussionForumVO;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/v1/content/forum")
@RequiredArgsConstructor
public class DiscussionForumManagerController {

    private final DiscussionForumManagerService discussionForumManagerService;


    @PostMapping(value = "/create")
    public ResponseEntity createDiscussionForum(@Valid @RequestBody CreateDiscussionForumRequest createDiscussionForumRequest,
                                                HttpServletRequest request) throws IOException {
        DiscussionForumVO discussionForumVO = new DiscussionForumVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        discussionForumVO.setUserId(Long.valueOf(userId));
        discussionForumVO.setTitle(createDiscussionForumRequest.title());
        discussionForumVO.setTags(createDiscussionForumRequest.tags());
        discussionForumVO.setDescription(createDiscussionForumRequest.body());
        return discussionForumManagerService.createDiscussionForum(discussionForumVO);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllDiscussionForums() {
        return discussionForumManagerService.getAllDiscussionForums();
    }

    @PutMapping(value = "/")
    public ResponseEntity updateDiscussionForum(@Valid @RequestBody UpdateDiscussionForumRequest updateDiscussionForumRequest,
                                                HttpServletRequest request) throws IOException {
        DiscussionForumVO discussionForumVO = new DiscussionForumVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        discussionForumVO.setUserId(Long.valueOf(userId));
        discussionForumVO.setDiscussionForumId(updateDiscussionForumRequest.forumId());
        discussionForumVO.setTitle(updateDiscussionForumRequest.title());
        discussionForumVO.setTags(updateDiscussionForumRequest.tags());
        discussionForumVO.setDescription(updateDiscussionForumRequest.body());
        return discussionForumManagerService.updateDiscussionForum(discussionForumVO);
    }
    @DeleteMapping(value = "/{forumId}")
    public ResponseEntity deleteDiscussionForum(@PathVariable("forumId") Long forumId){
        return discussionForumManagerService.deleteDiscussionForum(forumId);
    }
}
