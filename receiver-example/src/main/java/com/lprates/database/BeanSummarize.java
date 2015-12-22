package com.lprates.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 */
@Entity
public class BeanSummarize {

	@Id
	private int id;
	
	@Column(name = "sov", nullable = false)
	private long sum;

	public BeanSummarize() {
	} 
	
	/**
	 * Constructor for BeanSummarize.
	 * @param id int
	 * @param sum long
	 */
	public BeanSummarize(int id, long sum) {
		this.id = id;
		this.sum = sum;
	} 
		
	/**
	 * Method getId.
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method setId.
	 * @param id int
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method getSum.
	 * @return long
	 */
	public long getSum() {
		return sum;
	}

	/**
	 * Method setSum.
	 * @param sum long
	 */
	public void setSum(long sum) {
		this.sum = sum;
	}

	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString() {
		return "BeanSummarize [" + id + ": " + sum + "]";
	}
	
	
	
}
