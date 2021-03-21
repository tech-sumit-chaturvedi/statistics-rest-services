package com.n26.statistics;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsRestServicesApplicationTest {
	
	 @Test
	  public void applicationStarts() {
		 StatisticsRestServicesApplication.main(new String[] {});
	  }

}

