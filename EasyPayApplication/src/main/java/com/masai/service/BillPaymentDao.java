package com.masai.service;

import com.masai.exception.BillPaymentException;
import com.masai.model.BillPayment;

public interface BillPaymentDao {
	public BillPayment addBillPayment(BillPayment Payment,String key)throws BillPaymentException;
	public BillPayment ViewBillPayment(Integer id,String key)throws BillPaymentException;

}
