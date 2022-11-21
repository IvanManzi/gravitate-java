package com.content_manager_service.controller;

import com.content_manager_service.form.CreateTopicCommentRequest;
import com.content_manager_service.form.CreateTopicRequest;
import com.content_manager_service.form.UpdateTopicRequest;
import com.content_manager_service.service.GravitateTopicManagerService;
import com.model.TopicReplyVO;
import com.model.TopicVO;
import com.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value = "/api/v1/content/topic",produces = "application/json")
@RequiredArgsConstructor
public class GravitateTopicController {

    private final GravitateTopicManagerService gravitateTopicManagerService;


    @PostMapping(value = "/create")
    public ResponseEntity createTopic(@Valid @RequestBody CreateTopicRequest createTopicRequest, HttpServletRequest request) throws IOException {
        TopicVO topicVO = new TopicVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        topicVO.setUserId(Long.valueOf(userId));
        topicVO.setTitle(createTopicRequest.title());
        topicVO.setTags(createTopicRequest.tags());
        topicVO.setProblemDescription(createTopicRequest.description());
        topicVO.setTopicThumbnail(createTopicRequest.thumbnail());
        return gravitateTopicManagerService.createTopic(topicVO);
    }

    @PostMapping(value = "/comment")
    public ResponseEntity createTopicReply(@Valid @RequestBody CreateTopicCommentRequest createTopicCommentRequest, HttpServletRequest request) throws IOException {
        TopicReplyVO topicReplyVO = new TopicReplyVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        topicReplyVO.setUserId(Long.valueOf(userId));
        topicReplyVO.setTopicId(createTopicCommentRequest.topicId());
        topicReplyVO.setComment(createTopicCommentRequest.comment());
        return gravitateTopicManagerService.createTopicReply(topicReplyVO);
    }

    @GetMapping(value = "/team/")
    public ResponseEntity getAllTeamTopics(@RequestParam("managerId")Long managerId,@RequestParam(value = "search",required = false)String search){
        return gravitateTopicManagerService.getAllTeamTopics(managerId,search);
    }

    @PutMapping(value = "/")
    public ResponseEntity updateTopic(@Valid @RequestBody UpdateTopicRequest updateTopicRequest,HttpServletRequest request) throws IOException {
        TopicVO topicVO = new TopicVO();
        String token = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userId = JwtUtils.getUserIdFromJwtToken(token);
        topicVO.setTopicId(updateTopicRequest.topicId());
        topicVO.setUserId(Long.valueOf(userId));
        topicVO.setTitle(updateTopicRequest.title());
        topicVO.setTags(updateTopicRequest.tags());
        topicVO.setProblemDescription(updateTopicRequest.description());
        topicVO.setTopicThumbnail(updateTopicRequest.thumbnail());
        return gravitateTopicManagerService.updateTopic(topicVO);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteTopic(@PathVariable("topicId") Long topicId){
        return gravitateTopicManagerService.deleteTopic(topicId);
    }

}
