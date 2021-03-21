package com.n26.statistics.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.n26.statistics.model.StatisticsResp;
import com.n26.statistics.model.TransactionReq;
import com.n26.statistics.model.Transactions;

@Service
public class ServiceImpl implements ServiceI {
	
 private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);	
	
 private List<Transactions> transactionList = Collections.synchronizedList(new ArrayList<>());
 
 private List<TransactionReq> totalTXLastSixtySecsList = new ArrayList<>();
	
	
	/**
	 * This method is used to save data on Transaction List.
	 * @param transactionReq
	 * @author Sumit.Chaturvedi
	 * @since 2021-19-03 01:12
	 */
	@SuppressWarnings("serial")
	public boolean save(TransactionReq transactionReq) {
		logger.info(
				"Save()-------> on the bases of Current time, it will start saving Transaction to Transaction List.");
		transactionList.add(new Transactions() {{
				setZonedDateTime(ZonedDateTime.now());
				setTransactionReq(transactionReq);
			}});
		logger.info("Save()-------> Transaction Save Successfully, To the Transaction List.");
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
		ZonedDateTime currentDateTime = ZonedDateTime.now();
		logger.info("get()------->  CurrentDateTime +" + currentDateTime);
		ZonedDateTime currentDateTimeMinusSixtySecond = currentDateTime.minusSeconds(60);
		logger.info("get()------->  CurrentDateTime Minus Sixty Seconds +" + currentDateTimeMinusSixtySecond);

		for (int i = transactionList.size() - 1; i >= 0; i--) {
			if (currentDateTimeMinusSixtySecond.compareTo(transactionList.get(i).getZonedDateTime())
					* transactionList.get(i).getZonedDateTime().compareTo(currentDateTime) >= 0) {
				totalTXLastSixtySecsList.add(transactionList.get(i).getTransactionReq());
			}
		}
			
		logger.info(
				"get()------->  totalTXLastSixtySecsList -------> LIST CONTAINS TOTAL TRANSACTION COMPUTED ON THE LAST SIXTY SECONDS");
		if(totalTXLastSixtySecsList.isEmpty()) return null;
		return new StatisticsResp() {{
				setSum(getSumOfAmt());
				setAvg(getAvgOfAmt(totalTXLastSixtySecsList.size()));
				setCount((long) totalTXLastSixtySecsList.size());
				setMax(getMaxAmt());
				setMin(getMinAmt());
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
		BigDecimal sum = new BigDecimal(0.0);
		for (TransactionReq transactionReq : totalTXLastSixtySecsList)
			if (transactionReq != null)
				sum = sum.add(transactionReq.getAmount());
		return sum.setScale(2, RoundingMode.HALF_UP);
	   //return sum.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * This method is used to Calculate Average Value and Return.
	 * @return BigDecimal
	 * @author Sumit.Chatuvedi
	 * @since 2021-19-03 03:12
	 */
	private BigDecimal getAvgOfAmt(int count) {
		logger.info("getAvgOfAmt() -------> Method Invoked");
		try {
			return getSumOfAmt().divide(new BigDecimal(count)).setScale(2, RoundingMode.HALF_UP);
		} catch (ArithmeticException e) {
			return new BigDecimal(0);
		}

	}
	
	
	/**
	 * This method is used to return Maximum amount of LastMinuteStatistics List.
	 * @return BigDecimal
	 * @author Sumit.Chatuvedi
	 * @since 2021-19-03 03:17
	 */
	private BigDecimal getMaxAmt() {
		logger.info("getMaxAmt() -------> Method Invoked");
		return totalTXLastSixtySecsList.stream().max(Comparator.comparing(Request -> Request.getAmount())).get().getAmount().setScale(2, RoundingMode.HALF_UP);
	}
	
	
	/**
	 * This method is used to return minimum amount of LastMinuteStatistics List.
	 * @return BigDecimal
	 * @author Sumit.Chatuvedi
	 * @since 2021-19-03 01:12
	 */
	private BigDecimal getMinAmt() {
		logger.info("getMinAmt() -------> Method Invoked");
		return totalTXLastSixtySecsList.stream().min(Comparator.comparing(Request -> Request.getAmount())).get().getAmount().setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * This method is used to Removes all of the elements from the Transaction List.
	 * @return boolean
	 * @author Sumit.Chatuvedi
	 * @since 2021-20-03 11:12
	 */
	public boolean delete() {
		logger.info("delete()------->  Removes all of the elements from the Transaction List.");
		transactionList.clear();
		totalTXLastSixtySecsList.clear();
		return true;
	}





}