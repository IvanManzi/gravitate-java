package com.project_manager_service.controller;


import com.project_manager_service.form.AssignProjectsToUserRequest;
import com.project_manager_service.service.GravitateUserProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/project/",produces = "application/json")
@RequiredArgsConstructor
public class GravitateUserProjectController {

    private final GravitateUserProjectService gravitateUserProjectService;

    @PostMapping(value = "/assign")
    public boolean assignProjectToGravitateUser(@RequestBody AssignProjectsToUserRequest assignProjectsToUserRequest){
        return gravitateUserProjectService.assignUserToProject(assignProjectsToUserRequest.userId(), assignProjectsToUserRequest.projects());
    }

    @GetMapping(value = "/assigned")
    public ResponseEntity getProjectsAssignedToGravitateUsers(){
        return gravitateUserProjectService.getUsersAssignedToProjects();
    }

    @GetMapping(value = "/")
    public ResponseEntity getGravitateUserProjects(@RequestParam("userId") Long userId){
        return gravitateUserProjectService.getGravitateUserProjects(userId);
    }
}
