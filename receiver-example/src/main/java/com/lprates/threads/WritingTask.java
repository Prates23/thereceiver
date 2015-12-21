package com.lprates.threads;

import java.util.List;

import com.lprates.bean.DummyBean;

public class WritingTask extends Thread{

	private List<DummyBean> dummyBeans;
	
	
	
	public WritingTask(List<DummyBean> dummyBeans){
		this.dummyBeans= dummyBeans;
	}
	
	public void run(){
		
		
		
	}
}
