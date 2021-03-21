package com.n26.statistics.service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import com.n26.statistics.model.TransactionReq;
import com.n26.statistics.model.Transactions;
@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest {
	
	@InjectMocks
	@Spy
	ServiceImpl serviceImpl = new ServiceImpl();
	
	 @Mock
	 Logger logger = LoggerFactory.getLogger(ServiceImpl.class);	
			 
	 @Mock
	 List<TransactionReq> totalTXLastSixtySecsList = new ArrayList<>();
	

	  @Before 
	  public void Setup() {
		  MockitoAnnotations.initMocks(this);
	  }	 
	
	@Test
	public void testSave() {
		serviceImpl.save(new TransactionReq());
	}
	
	@SuppressWarnings("serial")
	@Test
	public void testGet() {
		ZonedDateTime zoneDateTime  = ZonedDateTime.of(2020, 12, 3, 12, 20, 59, 90000, ZoneId.systemDefault());
		List<Transactions> transactionList = new ArrayList<>() {{
			add(new Transactions() {{
				setZonedDateTime(zoneDateTime);
			}});
			add(new Transactions() {{
				setZonedDateTime(zoneDateTime);
			}});
		}};
		
		List<TransactionReq> totalTXLastSixtySecsList  = new ArrayList<>() {{
			add(new TransactionReq() {{
				setAmount(new BigDecimal(43));
			}});
			add(new TransactionReq() {{
				setAmount(new BigDecimal(49));
			}});
			
		}};
		ReflectionTestUtils.setField(serviceImpl, "transactionList", transactionList);
		ReflectionTestUtils.setField(serviceImpl, "totalTXLastSixtySecsList", totalTXLastSixtySecsList);
		serviceImpl.get();
	}
	
	@Test
	public void testDelete() {
		serviceImpl.delete();
	}


	

	

	

}
