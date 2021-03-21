package com.n26.statistics.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Sumit.Chaturvedi
 * @since 2021-18-03 23:24
 */
public class TransactionReq implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionReq.class);	
	private static final long serialVersionUID = 4926468583005750707L;
	
	
	
	
	/*
	 * @DecimalMin(value = "0.0", inclusive = false) 
	 * @Digits(integer=3, fraction=2)
	 */
	private BigDecimal amount;
	
	private ZonedDateTime timestamp;
	
	//need default constructor for JSON Parsing
		public TransactionReq(){
		}

		/**
		 * @return the amount
		 */
		public BigDecimal getAmount() {
			return amount;
		}

		/**
		 * @param amount the amount to set
		 */
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		/**
		 * @return the timestamp
		 */
		public ZonedDateTime getTimestamp() {
			return timestamp;
		}

		/**
		 * @param timestamp the timestamp to set
		 */
		public void setTimestamp(ZonedDateTime timestamp) {
			this.timestamp = timestamp;
		}
		
		/**
		 * @return the timestamp
		 */
		public int getSecond() {
			return getTimestamp().getSecond();
		}
		/**
		 * This method is used to  Check Whether Timestamp is not older than 60s.
		 * @return Boolean
		 * @author Sumit.Chatuvedi
		 * @since 2021-21-03 04:19
		 */
		public boolean isOlderThanSixtySeconds() {
			logger.info("isOlderThanSixtySeconds()-------> isOlderThanSixtySeconds Method Invoked.");
			ZonedDateTime currentDateTime = ZonedDateTime.now();
			logger.info("System Current Date Time -------> "+  ZonedDateTime.now());
			ZonedDateTime currentDateTimeMinusSixtySecond = currentDateTime.minusSeconds(60);
			logger.info("System current Date Time Minus SixtySecond -------> "+  currentDateTime.minusSeconds(60));
			logger.info("isOlderThanSixtySeconds()-------> Check Whether Timestamp is not older than 60s.");
			return !currentDateTimeMinusSixtySecond.isBefore(getTimestamp());
		}
		
		
		/**
		 * This method is used to Check Whether Timestamp is not older than 60s.
		 * @return Boolean
		 * @author Sumit.Chatuvedi
		 * @since 2021-21-03 03:17
		 */
		public boolean isFutureTransaction(ZonedDateTime timeStamp) {
			logger.info("isFutureTransaction()-------> isFutureTransaction Method Invoked.");
			ZonedDateTime currentDateTime = ZonedDateTime.now();
			logger.info("System Current Date Time()------->" + ZonedDateTime.now());
			logger.info("Transaction TimeStamp Client Side()------->" + timeStamp);
			logger.info("isOlderThanSixtySeconds()-------> Check Whether Transaction TimeStamp is in Future.");
			return timeStamp.isAfter(currentDateTime);
		}
}
		
		
		 
		
		

	
	

