package com.lprates.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BeanSummarize {

	@Id
	private int id;
	
	@Column(name = "sov", nullable = false)
	private long sum;

	public BeanSummarize() {
	} 
	
	public BeanSummarize(int id, long sum) {
		this.id = id;
		this.sum = sum;
	} 
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "BeanSummarize [" + id + ": " + sum + "]";
	}
	
	
	
}
