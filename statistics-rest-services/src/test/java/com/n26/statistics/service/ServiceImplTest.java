package com.n26.statistics.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentHashMap;

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
import com.n26.statistics.model.StatisticsResp;
import com.n26.statistics.model.TransactionReq;
@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest {
	
	@InjectMocks
	@Spy
	ServiceImpl serviceImpl = new ServiceImpl();
	
	 @Mock
	 Logger logger = LoggerFactory.getLogger(ServiceImpl.class);	
			 
	 @Mock
	 ConcurrentHashMap<ZonedDateTime, BigDecimal> totalTxnsLastSixtySecs = new ConcurrentHashMap<>();
	 
	 @Mock
	 private static BigDecimal min;
	 
	 @Mock
	 private static BigDecimal max;
	

	  @Before 
	  public void Setup() {
		  MockitoAnnotations.initMocks(this);
	  }	 

		@SuppressWarnings("serial")
		@Test
		public void testSave() {
			BigDecimal min = new BigDecimal(1);
			ReflectionTestUtils.setField(serviceImpl, "min", min);
			BigDecimal max = new BigDecimal(2);
			ReflectionTestUtils.setField(serviceImpl, "max", max);
			Boolean isCreated = serviceImpl.save(new TransactionReq() {{
					setAmount(new BigDecimal(34));
					setTimestamp(ZonedDateTime.now());
				}});
			assertEquals(Boolean.TRUE, isCreated);
		}
	
	
	@Test
	public void testGet() {
		totalTxnsLastSixtySecs.put(ZonedDateTime.now(), new BigDecimal(12));
		ReflectionTestUtils.setField(serviceImpl, "totalTxnsLastSixtySecs", totalTxnsLastSixtySecs);
		min = new BigDecimal(43);
		max = new BigDecimal(22);
		ReflectionTestUtils.setField(serviceImpl, "min", min);
		ReflectionTestUtils.setField(serviceImpl, "max", max);
		StatisticsResp resp = serviceImpl.get();
		assertNotNull(resp);
	}
	
	
	@Test
	public void testDelete() {
		Boolean isDeleted = serviceImpl.delete();
		assertEquals(Boolean.TRUE, isDeleted);
	}


	

	

	

}
