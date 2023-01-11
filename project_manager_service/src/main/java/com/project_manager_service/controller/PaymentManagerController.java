package com.project_manager_service.controller;


import com.model.PaymentVO;
import com.project_manager_service.service.PaymentManagerService;
import com.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/project/payment",produces = "application/json")
@RequiredArgsConstructor
public class PaymentManagerController {


    private final PaymentManagerService paymentManagerService;


    @PutMapping(value = "/monthly/payment-status")
    public ResponseEntity<APIResponse> markTasksAndIncentivesAsPaid(@RequestParam("userId") Long userId,
                                                                    @RequestParam("month") Integer month,
                                                                    @RequestParam("year") Integer year,
                                                                    @RequestParam("total") Double total,
                                                                    @RequestParam("projectId") Long projectId){
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setUserId(userId);
        paymentVO.setProjectId(projectId);
        paymentVO.setMonth(month);
        paymentVO.setYear(year);
        paymentVO.setTotalAmount(total);
        return paymentManagerService.markMonthlyTasksAndIncentivesAsPaid(paymentVO);
    }
}
