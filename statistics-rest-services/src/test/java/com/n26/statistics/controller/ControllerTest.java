package com.n26.statistics.controller;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.n26.statistics.model.StatisticsResp;
import com.n26.statistics.model.TransactionReq;
import com.n26.statistics.model.Transactions;
import com.n26.statistics.service.ServiceI;
import com.n26.statistics.service.ServiceImpl;

/**
 * This controller contains get Statistics methods.
 * @author Sumit.Chaturvedi
 * @since 2021-18-03 23:24
 */

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
	
	
	@InjectMocks
	@Spy
	Controller controller = new Controller();
	
	@Mock
	ServiceI serviceI = new ServiceImpl();
	
	@Before
	public void Setup() {
		MockitoAnnotations.initMocks(this);
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
		ReflectionTestUtils.setField(serviceI, "transactionList", transactionList);
		ReflectionTestUtils.setField(serviceI, "totalTXLastSixtySecsList", totalTXLastSixtySecsList);
		StatisticsResp statisticsResp = new StatisticsResp() {{
			setSum(new BigDecimal(45));
			setAvg(new BigDecimal(44));
			setMax(new BigDecimal(67));
			setMin(new BigDecimal(4));
			setCount(new Long(3));
		}};
		
		
		Mockito.when(serviceI.get()).thenReturn(statisticsResp);
		controller.get();
	}
	
	
	
	@SuppressWarnings("serial")
	@Test
	public void testSave() throws Exception  {
		controller.save(new TransactionReq() {{
			setAmount(new BigDecimal(44));
			setTimestamp(ZonedDateTime.of(2015, 12, 3, 12, 20, 59, 90000, ZoneId.systemDefault()));
		}});
	}
	
	
	@SuppressWarnings("serial")
	@Test
	public void testSaveFuture() throws Exception  {
		controller.save(new TransactionReq() {{
			setAmount(new BigDecimal(44));
			setTimestamp(ZonedDateTime.of(2030, 12, 3, 12, 20, 59, 90000, ZoneId.systemDefault()));
		}});
	}
	

	@Test
	public void testSaveNow() throws Exception {
		/*TransactionReq transactionReq = new TransactionReq() {
			{
				setAmount(new BigDecimal(44));
				setTimestamp(ZonedDateTime.now());
			}
		};*/
		
		TransactionReq transactionReq = Mockito.mock(TransactionReq.class);
		Mockito.when(transactionReq.getAmount()).thenReturn(new BigDecimal(44));
		Mockito.when(transactionReq.getTimestamp()).thenReturn(ZonedDateTime.now());
		
		Mockito.when(serviceI.save(transactionReq)).thenReturn(Mockito.anyBoolean());
		controller.save(transactionReq);
	}
	 

}
