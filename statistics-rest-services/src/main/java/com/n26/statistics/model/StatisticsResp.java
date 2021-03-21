package com.n26.statistics.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
/**
 * @author Sumit.Chaturvedi
 * @since 2021-18-03 23:24
 */
public class StatisticsResp implements Serializable {
	
	


	private static final long serialVersionUID = 4926468583005750707L;
	
	private BigDecimal sum;

	private BigDecimal avg;

	private BigDecimal max;

	private BigDecimal min;

	private Long count;
	


	public StatisticsResp() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BigDecimal getSum() {
		return sum;
	}



	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}



	public BigDecimal getAvg() {
		return avg;
	}



	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}



	public BigDecimal getMax() {
		return max;
	}



	public void setMax(BigDecimal max) {
		this.max = max;
	}



	public BigDecimal getMin() {
		return min;
	}



	public void setMin(BigDecimal min) {
		this.min = min;
	}



	public Long getCount() {
		return count;
	}



	public void setCount(Long count) {
		this.count = count;
	}
	
	/*
	 * @Override public String toString() { return "StatisticsResp [sum=" + sum +
	 * ", avg=" + avg + ", max=" + max + ", min=" + min + ", count=" + count +
	 * ", timestamp=" + timestamp + "]"; }
	 */

}
