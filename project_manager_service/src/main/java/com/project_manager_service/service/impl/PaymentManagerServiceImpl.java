package com.project_manager_service.service.impl;

import com.model.PaymentVO;
import com.project_manager_service.dao.PaymentDao;
import com.project_manager_service.dao.ProjectIncentiveDao;
import com.project_manager_service.dao.TaskReportDao;
import com.project_manager_service.service.PaymentManagerService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentManagerServiceImpl implements PaymentManagerService {

    private final TaskReportDao taskReportDao;


    private final PaymentDao paymentDao;

    private final ProjectIncentiveDao projectIncentiveDao;

    @Override
    public ResponseEntity<APIResponse> markMonthlyTasksAndIncentivesAsPaid(PaymentVO paymentVO) {
        int result = taskReportDao.markMonthlyTasksAsPaid(paymentVO.getUserId(),paymentVO.getMonth(), paymentVO.getYear(), paymentVO.getProjectId());
        //mark the current month incentives as paid
        int result2 = projectIncentiveDao.markMonthlyProjectIncentiveAsPaid(paymentVO.getMonth(),paymentVO.getYear());
        //create payment record
        int result3 = paymentDao.createPaymentRecord(paymentVO);
        if((result > 0) && (result2 > 0) && (result3 > 0) ){
            return APIResponse.resultSuccess("User task reports marked as paid.");
        }
        return APIResponse.resultFail();
    }





}
