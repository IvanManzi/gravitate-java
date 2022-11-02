package com.user_manager_service.dao;

import com.model.UserVO;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface userDao extends CrudRepository<UserVO, UUID> {
}
