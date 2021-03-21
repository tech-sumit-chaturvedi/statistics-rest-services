package com.n26.statistics.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
/**
 * @author Sumit.Chaturvedi
 * @since 2021-18-03 23:24
 */
public class Transactions implements Serializable{
	
	private static final long serialVersionUID = 4926468583005750707L;
	
	private ZonedDateTime zonedDateTime;
	private TransactionReq transactionReq;
	
	
	
	public TransactionReq getTransactionReq() {
		return transactionReq;
	}
	public void setTransactionReq(TransactionReq transactionReq) {
		this.transactionReq = transactionReq;
	}
	public ZonedDateTime getZonedDateTime() {
		return zonedDateTime;
	}
	public void setZonedDateTime(ZonedDateTime zonedDateTime) {
		this.zonedDateTime = zonedDateTime;
	}
	
	

}
