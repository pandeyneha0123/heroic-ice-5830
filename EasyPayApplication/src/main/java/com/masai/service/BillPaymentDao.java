package com.masai.service;

import com.masai.exception.BillPaymentException;
import com.masai.model.BillPayment;

public interface BillPaymentDao {
	public BillPayment addBillPayment(BillPayment Payment)throws BillPaymentException;
	public BillPayment ViewBillPayment(Integer id)throws BillPaymentException;

}
