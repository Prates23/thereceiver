package com.lprates.bean;

import com.lprates.monitor.TheReceiverDummy;

public class BeanConsumer implements Runnable {
	private TheReceiverDummy receiver;
	private boolean running;

	public BeanConsumer(TheReceiverDummy receiver) {
		this.receiver = receiver;
	}

	public void terminate() {
		running = false;
	}
	
	public void run() {
		running = true;
		System.out.println("BeanConsumer: started running");
		
		while (running) {

			receiver.waitFilling();
			receiver.drainFill();
		}
		
		receiver.shutdown();
	}
}