package com.project_manager_service.service.impl;

import com.model.PaymentVO;
import com.project_manager_service.dao.PaymentDao;
import com.project_manager_service.dao.ProjectIncentiveDao;
import com.project_manager_service.dao.TaskReportDao;
import com.project_manager_service.service.PaymentManagerService;
import com.util.APIResponse;
import com.util.DateUtil;
import com.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentManagerServiceImpl implements PaymentManagerService {

    private final TaskReportDao taskReportDao;

    private final PaymentDao paymentDao;

    private final ProjectIncentiveDao projectIncentiveDao;

    @Override
    public ResponseEntity<APIResponse> markMonthlyTasksAndIncentivesAsPaid(PaymentVO paymentVO) {
        Calendar ca =  Calendar.getInstance();
        int currentMonth = ca.get(Calendar.MONTH);
        currentMonth++;
        if(currentMonth == paymentVO.getMonth()){
            if(DateUtil.getCurrentDayEndTime().compareTo(DateUtil.getCurrentMonthEndTime()) == 0){
                taskReportDao.markMonthlyTasksAsPaid(paymentVO.getUserId(),paymentVO.getMonth(), paymentVO.getYear(), paymentVO.getProjectId());
                //mark the current month incentives as paid
                projectIncentiveDao.markMonthlyProjectIncentiveAsPaid(paymentVO.getUserId(),paymentVO.getMonth(),paymentVO.getYear(),paymentVO.getProjectId());
                //create payment record
                int result3 = paymentDao.createPaymentRecord(paymentVO);
                if(result3 > 0 ){
                    return APIResponse.resultSuccess("User task reports marked as paid.");
                }
                return APIResponse.resultFail();
            }
            return APIResponse.resultFail("You can't mark tasks as paid until the end of month. ");
        }
        taskReportDao.markMonthlyTasksAsPaid(paymentVO.getUserId(),paymentVO.getMonth(), paymentVO.getYear(), paymentVO.getProjectId());
        //mark the current month incentives as paid
        projectIncentiveDao.markMonthlyProjectIncentiveAsPaid(paymentVO.getUserId(),paymentVO.getMonth(),paymentVO.getYear(),paymentVO.getProjectId());
        //create payment record
        int result3 = paymentDao.createPaymentRecord(paymentVO);
        if(result3 > 0 ){
            return APIResponse.resultSuccess("User task reports marked as paid.");
        }
        return APIResponse.resultFail();
    }

    @Override
    public ResponseEntity<APIResponse> undoMarkedTasksAndIncentivesAsPaid(PaymentVO paymentVO) {
        //check if payment exists
        PaymentVO payment = paymentDao.checkIfPaymentExists(paymentVO);
        log.info("{} ",payment.getPaymentId());
        if(ValidationUtil.isNullObject(payment)){
            return APIResponse.resultFail("Payment doesn't exist. ");
        }
        taskReportDao.unDomarkMonthlyTasksAsPaid(paymentVO.getUserId(),paymentVO.getMonth(), paymentVO.getYear(), paymentVO.getProjectId());
        projectIncentiveDao.unDomarkMonthlyProjectIncentiveAsPaid(payment.getUserId(),paymentVO.getMonth(),paymentVO.getYear(),paymentVO.getProjectId());

        int result = paymentDao.deletePaymentRecord(payment);
        if(result > 0){
            return APIResponse.resultSuccess();
        }
        return APIResponse.resultFail();
    }


}
