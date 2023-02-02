package com.user_manager_service.service.impl;

import com.model.AdditionalPointVO;
import com.user_manager_service.dao.AdditionalPointDao;
import com.user_manager_service.dao.UserSkillDao;
import com.user_manager_service.form.CreateAdditionalPointRequest;
import com.user_manager_service.service.AdditionalPointsManagerService;
import com.util.APIResponse;
import com.util.JwtUtils;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdditionalPointsManagerServiceImpl implements AdditionalPointsManagerService {

    private final AdditionalPointDao additionalPointDao;

    private final UserSkillDao userSkillDao;

    private final RestTemplate restTemplate;


    @Override
    public ResponseEntity createAdditionalPoint(CreateAdditionalPointRequest createAdditionalPointRequest, String token) throws IOException {
        List<Long> rejected = new ArrayList<>();
        AdditionalPointVO additionalPointVO = new AdditionalPointVO();
        String adminId = JwtUtils.getUserIdFromJwtToken(token);
        additionalPointVO.setAdminId(Long.valueOf(adminId));
        additionalPointVO.setUserId(createAdditionalPointRequest.userId());
        additionalPointVO.setCategory(createAdditionalPointRequest.category());
        additionalPointVO.setComment(createAdditionalPointRequest.comment());
        additionalPointVO.setUserSkillId(createAdditionalPointRequest.userSkillId());
        additionalPointVO.setQuarter(createAdditionalPointRequest.quarter());
        additionalPointVO.setPoints(createAdditionalPointRequest.points());
        //check if the selected skill, blog or forum answer wasn't awarded points already
        int check1, check2, check3;
        if(!ValidationUtil.isNullObject(additionalPointVO.getUserSkillId())){
            check1 = additionalPointDao.isSkillAwarded(additionalPointVO);
            if(check1 == 1){
                return APIResponse.resultFail("Skill was already awarded points. ");
            }
            userSkillDao.markSkillAsAwarded(additionalPointVO.getUserSkillId());
            int result = additionalPointDao.createAdditionalPoint(additionalPointVO);
            if(result > 0){
                return APIResponse.resultSuccess();
            }
            return APIResponse.resultFail();
        } else if (!ValidationUtil.isNullObject(createAdditionalPointRequest.blogIds()) && !createAdditionalPointRequest.blogIds().isEmpty()) {
            //loop through the list and award points where necessary
            for(Long blogId : createAdditionalPointRequest.blogIds())
            {
                additionalPointVO.setBlogId(blogId);
                check2 = additionalPointDao.isBlogAwarded(additionalPointVO);
                if(check2 == 1){
                    rejected.add(blogId);
                    continue;
                }
                //call content manager to update is awarded status of the blog.
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<Void> request = new HttpEntity<>(headers);
                Map<String, Object> params = new HashMap<>();
                params.put("blogIds", additionalPointVO.getBlogId());
                params.put("status", true);
                ResponseEntity<Boolean> response = restTemplate.exchange(
                        "http://CONTENT-MANAGER-SERVICE/api/v1/content/blog/{blogIds}/isAwarded/{status}",
                        HttpMethod.PUT,
                        request,
                        Boolean.class,
                        params
                );
                log.info("{}",response.getBody());
                boolean responseBody = response.getBody();
                int result = additionalPointDao.createAdditionalPoint(additionalPointVO);
                if(result < 0){
                    rejected.add(blogId);
                    continue;
                }
            }
            return APIResponse.resultSuccess();
        } else if (!ValidationUtil.isNullObject(createAdditionalPointRequest.forumAnswerIds()) && !createAdditionalPointRequest.forumAnswerIds().isEmpty()) {
            //loop through the list and award points where necessary
            for(Long forumAnswerId : createAdditionalPointRequest.forumAnswerIds())
            {
                additionalPointVO.setForumAnswerId(forumAnswerId);
                check3 = additionalPointDao.isForumAnswerAwarded(additionalPointVO);
                if(check3 == 1){
                    rejected.add(forumAnswerId);
                    continue;
                }
                //call content manager to update is awarded status of the forum answer.
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<Void> request = new HttpEntity<>(headers);
                Map<String, Object> params = new HashMap<>();
                params.put("solutionId", additionalPointVO.getForumAnswerId());
                params.put("status", true);
                ResponseEntity<Boolean> response = restTemplate.exchange(
                        "http://CONTENT-MANAGER-SERVICE/api/v1/content/forum/solution/{solutionId}/isAwarded/{status}",
                        HttpMethod.PUT,
                        request,
                        Boolean.class,
                        params
                );
                log.info("{}",response.getBody());
                boolean responseBody = response.getBody();
                int result = additionalPointDao.createAdditionalPoint(additionalPointVO);
                if(result < 0){
                    rejected.add(forumAnswerId);
                    continue;
                }
            }

            return APIResponse.resultSuccess();
        }else{
            return APIResponse.resultFail();
        }

    }

    @Override
    public ResponseEntity getUserAdditionalPoints(Long userId, List<Integer> quarters, Integer year) {
        List<Map> userAdditionalPoints = additionalPointDao.getUserAdditionalPoints(userId, quarters, year);
        if(userAdditionalPoints.isEmpty()){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("USER_ADDITIONAL_POINTS",userAdditionalPoints);
        return APIResponse.resultSuccess(data);
    }
}
