package com.n26.statistics.service;

import org.springframework.stereotype.Service;
import com.n26.statistics.model.StatisticsResp;
import com.n26.statistics.model.TransactionReq;

/**
 * @author Sumit.Chaturvedi
 * @since 2021-19-03 00:24
 */
@Service
public interface ServiceI {
	public boolean save(TransactionReq transactionReq);
	public StatisticsResp get();
	public boolean delete();
}
