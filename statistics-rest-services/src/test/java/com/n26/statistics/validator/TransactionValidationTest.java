package com.n26.statistics.validator;

import java.math.BigDecimal;import java.security.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ValidationException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
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
@RunWith(MockitoJUnitRunner.class)
public class TransactionValidationTest {
	
	@InjectMocks
   TransactionValidation transactionValidation;	
	/**
	 * Validates the basic Transaction given transactionReq 
	 * @author Sumit.Chaturedi
	 * @since 2021-19-03 03:48
	 * @param transactionReq
	 * @throws Exception throws exception when validation fails
	 */
	@SuppressWarnings({ "static-access", "serial" })
	@Test
	public void testValidateTransaction () throws JsonProcessingException {
		transactionValidation.validateTransaction( new TransactionReq() {{
			setAmount(new BigDecimal(33.4));
			setTimestamp(ZonedDateTime.now());
		}});
	
	}
	
	@SuppressWarnings({ "static-access", "serial" })
	@Test
	public void testValidateTransactionForErrorMsgs () throws JsonProcessingException {
		TransactionReq transactionReq =  new TransactionReq() {{
			setAmount(null);
			setTimestamp(null);
		}};
		transactionValidation.validateTransaction(transactionReq);
	
	}
	
}
