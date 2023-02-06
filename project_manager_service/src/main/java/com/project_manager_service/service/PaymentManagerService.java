package com.project_manager_service.service;

import com.model.PaymentVO;
import com.util.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentManagerService {

    ResponseEntity<APIResponse> markMonthlyTasksAndIncentivesAsPaid(PaymentVO paymentVO);


    ResponseEntity<APIResponse> undoMarkedTasksAndIncentivesAsPaid(PaymentVO paymentVO);

}
