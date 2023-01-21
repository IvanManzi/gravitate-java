package com.user_manager_service.dao;


import com.model.JobVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobDao {

    int createJob(JobVO jobVO);

    List<JobVO> getAllJobs();

    int removeJob(Long jobId);



}
