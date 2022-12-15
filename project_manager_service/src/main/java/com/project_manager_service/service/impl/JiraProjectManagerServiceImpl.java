package com.project_manager_service.service.impl;

import com.project_manager_service.service.JiraProjectManagerService;
import com.util.APIResponse;
import com.util.MyJiraClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class JiraProjectManagerServiceImpl implements JiraProjectManagerService {


    @Override
    public ResponseEntity<APIResponse> getAllProjects() {
        MyJiraClient myJiraClient = MyJiraClient.instantiateClient();
        Map<String,Object> data = new HashMap<>();
        data.put("RESPONSE",myJiraClient.getAllProjects());
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity<APIResponse> getSingleProject(String url) {
        return null;
    }
}
