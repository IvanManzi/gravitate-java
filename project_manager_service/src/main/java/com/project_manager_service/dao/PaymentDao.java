package com.project_manager_service.dao;

import com.model.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

    int createPaymentRecord(PaymentVO paymentVO);
}
