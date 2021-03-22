package com.n26.statistics.controller;

import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentHashMap;
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
import com.n26.statistics.service.ServiceI;
import com.n26.statistics.service.ServiceImpl;

/**
 * This controller contains get Statistics methods.
 * 
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

	@Mock
	ServiceImpl serviceImpl = new ServiceImpl();

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

	@Test
	public void testGet() {
		totalTxnsLastSixtySecs.put(ZonedDateTime.now(), new BigDecimal(12));
		ReflectionTestUtils.setField(serviceImpl, "totalTxnsLastSixtySecs", totalTxnsLastSixtySecs);
		min = new BigDecimal(43);
		max = new BigDecimal(22);
		ReflectionTestUtils.setField(serviceImpl, "min", min);
		ReflectionTestUtils.setField(serviceImpl, "max", max);
		StatisticsResp resp = new StatisticsResp();
		TransactionReq transactionReq = Mockito.mock(TransactionReq.class);
		transactionReq.setAmount(new BigDecimal(34));
		transactionReq.setTimestamp(ZonedDateTime.now());
		Mockito.when(serviceI.get()).thenReturn(resp);
		controller.get();
	}

	@SuppressWarnings("serial")
	@Test
	public void testSaveTimeStampOlderSixtySec() throws Exception {
		assertNotNull(
		controller.save(new TransactionReq() {{
				setAmount(new BigDecimal(44));
				setTimestamp(ZonedDateTime.of(2015, 12, 3, 12, 20, 59, 90000, ZoneId.systemDefault()));
			}}));
	}

	@SuppressWarnings("serial")
	@Test
	public void testSaveTimeStampFuture() throws Exception {
		assertNotNull(
		controller.save(new TransactionReq() {{
				setAmount(new BigDecimal(44));
				setTimestamp(ZonedDateTime.of(2030, 12, 3, 12, 20, 59, 90000, ZoneId.systemDefault()));
			}}));
	}

	@Test
	public void testSaveTimeStampNow() throws Exception {
		TransactionReq transactionReq = Mockito.mock(TransactionReq.class);
		Mockito.when(transactionReq.getAmount()).thenReturn(new BigDecimal(44));
		Mockito.when(transactionReq.getTimestamp()).thenReturn(ZonedDateTime.now());
		Mockito.when(serviceI.save(transactionReq)).thenReturn(Mockito.anyBoolean());
		controller.save(transactionReq);
	}

	@Test
	public void delete() throws Exception {
		Mockito.when(serviceI.delete()).thenReturn(true);
		controller.delete();

	}

}
