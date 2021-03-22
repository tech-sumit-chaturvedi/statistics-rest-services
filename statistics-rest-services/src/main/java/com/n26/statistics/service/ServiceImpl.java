package com.n26.statistics.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.n26.statistics.model.StatisticsResp;
import com.n26.statistics.model.TransactionReq;

@Service
public class ServiceImpl implements ServiceI {
	
 private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);	
 
 private static long count = 0;
 private static BigDecimal min = null;
 private static BigDecimal max = null;
 private static ConcurrentHashMap<ZonedDateTime, BigDecimal> totalTxnsLastSixtySecs = new ConcurrentHashMap<>();
	
	
	
	/**
	 * This method is used to save data on totalTxnsLastSixtySecs M.
	 * @param transactionReq
	 * @author Sumit.Chaturvedi
	 * @since 2021-19-03 01:12
	 */
	public boolean save(TransactionReq transactionReq) {
		logger.info(
				"Save()-------> on the bases of Transaction request timestamp, it will start saving Transactions to the ConcurrentHashMap.");
		synchronized (ServiceImpl.class) {
			if (min == null) min = transactionReq.getAmount();
			if (max == null) max = transactionReq.getAmount();
			min = min.min(transactionReq.getAmount());
			max = max.max(transactionReq.getAmount());
			count++;
		}

		if (totalTxnsLastSixtySecs.containsKey(transactionReq.getTimestamp()))
			totalTxnsLastSixtySecs.put(transactionReq.getTimestamp(),
					totalTxnsLastSixtySecs.get(transactionReq.getTimestamp()).add(transactionReq.getAmount()));
		else
			totalTxnsLastSixtySecs.put(transactionReq.getTimestamp(), transactionReq.getAmount());
		logger.info("Save()-------> Transaction Save Successfully, To the totalTxnsLastSixtySecs Map.");
		return true;
	}
	
	
	
	
	/**
	 * Fetches total Transaction computed on the last the last 60 Second.
	 * 
	 * @return StatisticsResp
	 * @author Sumit.Chatuvedi
	 * @since 2021-19-03 01:19
	 */
	@SuppressWarnings("serial")
	public StatisticsResp get() {
		logger.info("get()------->  Method Invoked");
		logger.info(
				"get()------->  totalTxnsLastSixtySecs -------> MAP CONTAINS TOTAL TRANSACTION COMPUTED ON THE LAST SIXTY SECONDS");
		if(totalTxnsLastSixtySecs.isEmpty()) return null;
		return new StatisticsResp() {{
				setSum(getSumOfAmt());
				setAvg(getAvgOfAmt());
				setCount(count);
				setMax(max.setScale(2, RoundingMode.HALF_UP));
				setMin(min.setScale(2, RoundingMode.HALF_UP));
			}};
	}
	
	/**
	 * This method is used to Calculate Sum of Amount.
	 * @return BigDecimal
	 * @author Sumit.Chatuvedi
	 * @since 2021-19-03 02:12
	 */
	private BigDecimal getSumOfAmt() {
		logger.info("getSumOfAmt() -------> Method Invoked");
		return  totalTxnsLastSixtySecs.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * This method is used to Calculate Average Value and Return.
	 * @return BigDecimal
	 * @author Sumit.Chatuvedi
	 * @since 2021-19-03 03:12
	 */
	private BigDecimal getAvgOfAmt() {
		logger.info("getAvgOfAmt() -------> Method Invoked");
		try {
			System.out.println(getSumOfAmt());
			System.out.print(count);
			return getSumOfAmt().divide(new BigDecimal(count)).setScale(2, RoundingMode.HALF_UP);
		} catch (ArithmeticException e) {
			return new BigDecimal(0);
		}

	}

	/**
	 * This method is used to Removes all of the elements from the Transaction Map.
	 * @return boolean
	 * @author Sumit.Chatuvedi
	 * @since 2021-20-03 11:12
	 */
	public boolean delete() {
		logger.info("delete()------->  Removes all of the elements from the Transaction Map.");
		count = 0;
		max = null;
		min = null;
		if(totalTxnsLastSixtySecs.isEmpty()) return false;
		totalTxnsLastSixtySecs.clear();
		return true;
	}





}