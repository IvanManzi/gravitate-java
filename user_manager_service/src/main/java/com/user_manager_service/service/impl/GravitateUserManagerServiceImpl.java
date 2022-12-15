package com.user_manager_service.service.impl;

import com.user_manager_service.form.AssignProjectsToUserRequest;
import com.util.APIResponse;
import com.model.UserVO;
import com.user_manager_service.dao.UserDao;
import com.user_manager_service.service.GravitateUserManagerService;
import com.util.UserDetailsService;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.util.Constants.*;

@Service
@RequiredArgsConstructor
public class GravitateUserManagerServiceImpl implements GravitateUserManagerService,org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDao userDao;

    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public UserDetailsService loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        UserVO user = userDao.getGravitateUserByUsername(username);
        if(ValidationUtil.isNullObject(user)){
            throw new UsernameNotFoundException("Invalid username " + username);
        }
        /*Collection<RoleVO> roles = roleDao.getGravitateUserRole(user.getUserId());
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));*/
        switch (user.getUserLevel()) {
            case 1 -> authorities.add(new SimpleGrantedAuthority(ADMIN_USER));
            case 2 -> authorities.add(new SimpleGrantedAuthority(PROJECT_LEAD));
            case 3 -> authorities.add(new SimpleGrantedAuthority(DEVELOPER_USER));
            default -> authorities.add(new SimpleGrantedAuthority(CLIENT_USER));
        }
        return UserDetailsService.build(user,authorities);
    }


    @Override
    public ResponseEntity createGravitateUser(UserVO userVO,List<Long> projects, String token) {
        //check if email is unique
        int check = userDao.checkIfUsernameExists(userVO.getEmail());
        if(check == 1){
            return APIResponse.resultFail("Email already taken.");
        }
        int result = userDao.createGravitateUser(userVO);
        if(result > 0){
            //check if user has assigned projects
            if(ValidationUtil.isNullObject(projects)){
                return  APIResponse.resultSuccess();
            }else{
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                AssignProjectsToUserRequest assignProjectsToUserRequest = new AssignProjectsToUserRequest(userVO.getUserId(),projects);
                HttpEntity<AssignProjectsToUserRequest> request = new HttpEntity<AssignProjectsToUserRequest>(assignProjectsToUserRequest, headers);
                //call content manager service to assign projects to user
                boolean response = restTemplate.postForObject(
                        "http://PROJECT-MANAGER-SERVICE/api/v1/project/assign",
                        request,
                        boolean.class
                );
                if(response)
                    return APIResponse.resultSuccess();
                else
                    return APIResponse.resultFail("Failed to assign projects. ");
            }
        }else{
            return  APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getAllGravitateUsers(String search,Long roleId) {
        List<Map> users = userDao.getAllGravitateUsers(search,roleId);
        if(users.isEmpty()){
            return  APIResponse.resultFail("No users found.");
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("USERS",users);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity getGravitateUserById(Long userId) {
        UserVO userVO = userDao.getGravitateUserById(userId);
        if(ValidationUtil.isNullObject(userVO)){
            return APIResponse.resourceNotFound();
        }
        Map<String,Object> data = new HashMap<>();
        data.put("GRAVITATE_USER",userVO);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity updateGravitateUser(UserVO userVO,List<Long> projects, String token) {
        int result = userDao.updateGravitateUser(userVO);
        if(result > 0){
            if(ValidationUtil.isNullObject(projects)){
                return APIResponse.resultSuccess();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);
            AssignProjectsToUserRequest assignProjectsToUserRequest = new AssignProjectsToUserRequest(userVO.getUserId(),projects);
            HttpEntity<AssignProjectsToUserRequest> request = new HttpEntity<AssignProjectsToUserRequest>(assignProjectsToUserRequest, headers);
            //call content manager service to update changes
            boolean response = restTemplate.postForObject(
                    "http://CONTENT-MANAGER-SERVICE/api/v1/content/project/assign",
                    request,
                    boolean.class
            );
            if(response)
                return APIResponse.resultSuccess();

            return APIResponse.resultFail("Failed to assign projects. ");
        }else{
            return APIResponse.resultFail();
        }
    }


    @Override
    public ResponseEntity deleteGravitateUser(Long userId) {
        int result = userDao.deleteGravitateUser(userId);
        if(result > 0){
            return APIResponse.resultSuccess("User successfully deleted.");
        }else{
            return  APIResponse.resultFail();
        }
    }

    @Override
    public ResponseEntity getGravitateUserByUsername(String username) {
        UserVO user = userDao.getGravitateUserByUsername(username);
        if(ValidationUtil.isNullObject(user)){
            return  APIResponse.resultFail("User not found");
        }
        Map<String,Object> data = new HashMap<>();
        data.put("GRAVITATE_USER",user);
        return APIResponse.resultSuccess(data);
    }

    @Override
    public ResponseEntity updateGravitateUserPassword(UserVO userVO, String oldPassword) {
        //verify old password
        UserVO user = userDao.getGravitateUserById(userVO.getUserId());
        if(user.getPassword().equals(oldPassword)){
            userDao.updateGravitateUserPassword(userVO);
            return APIResponse.resultSuccess("Password successfully updated. ");
        }
        return APIResponse.resultFail("Invalid one time password. ");

    }

    @Override
    public ResponseEntity getGravitateUserTeamMembers() {
        List<Map> teamMembers = userDao.getGravitateUserTeamMembers();
        if(teamMembers.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("TEAM_MEMBERS",teamMembers);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity getGravitateManagerUsers(String search) {
        List<UserVO> managers = userDao.getGravitateManagerUsers(search);
        if(managers.isEmpty()){
            return APIResponse.resourceNotFound();
        }else{
            Map<String,Object> data = new HashMap<>();
            data.put("MANAGERS",managers);
            return APIResponse.resultSuccess(data);
        }
    }

    @Override
    public ResponseEntity disableGravitateUserAccount(Long userId) {
        int result = userDao.updateUserStatus(userId);
        if(result > 0){
            return APIResponse.resultSuccess();
        }else{
            return APIResponse.resultFail();
        }
    }
}
