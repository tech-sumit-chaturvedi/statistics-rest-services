package com.n26.statistics.validator;

import java.util.HashMap;
import java.util.Map;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.n26.statistics.base.BaseController;
import com.n26.statistics.model.TransactionReq;
import com.n26.statistics.util.StatisticsUtil;


/**
 * Validation for the Transaction request is performed here  
 *
 */
public class TransactionValidation {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionValidation.class);	
	
	/**
	 * Validates the basic Transaction given transactionReq 
	 * @author Sumit.Chaturedi
	 * @since 2021-19-03 03:48
	 * @param transactionReq
	 * @throws Exception throws exception when validation fails
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public static final void validateTransaction (TransactionReq transactionReq) throws JsonProcessingException {
		logger.info("validateTransaction()-------> validateTransaction Method Invoked");
		Map<String, Object> msgs = new HashMap<>();
		logger.info("validateTransaction()-------> Validate amount and timestamp is null or not");
		if(transactionReq.getAmount() == null) 	msgs.put("amount", "Amount cannot be null");
		if(transactionReq.getTimestamp() == null) msgs.put("timestamp", "Timestamp cannot be null");
		
		if(!msgs.isEmpty()) {
			Map<String, Object> errorMsgs = new HashMap<>();
			errorMsgs.put(BaseController.SUCCESS_KEY, false);
			errorMsgs.put(BaseController.STATUS, HttpStatus.BAD_REQUEST);
			errorMsgs.put(BaseController.DATA_KEY,new HashMap() {{
				put(BaseController.ERRORS_KEY, msgs);
			}});
			errorMsgs.put(BaseController.MESSAGE_KEY, "Unable to Save");
			String json = StatisticsUtil.mapToJson(errorMsgs);
			throw new ValidationException(json);
		}
	}
	
}
