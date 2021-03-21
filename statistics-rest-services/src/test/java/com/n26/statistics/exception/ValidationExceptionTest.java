package com.n26.statistics.exception;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidationExceptionTest {
	@Test
	public void ValidationException() {
		new ValidationException("message");
	}
}
