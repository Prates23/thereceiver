package com.lprates.bean;

import com.lprates.monitor.TheReceiverDummy;

/**
 */
public class BeanConsumer implements Runnable {
	private static final String TAG = BeanConsumer.class.getName();
	
	private TheReceiverDummy receiver;
	private boolean running;

	/**
	 * Constructor for BeanConsumer.
	 * @param receiver TheReceiverDummy
	 */
	public BeanConsumer(TheReceiverDummy receiver) {
		this.receiver = receiver;
	}

	public void terminate() {
		running = false;
		receiver.shutdown();
	}
	
	/**
	 * Method run.
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		running = true;
		System.out.println(TAG + ": BeanConsumer: started running");
		
		while (running) {
			System.out.println(TAG + ": waiting...");
			receiver.waitFilling();
			System.out.println(TAG + ": draining...");
			receiver.drainFill();
		}

		System.out.println(TAG + ": BeanConsumer has exited running loop");

		System.out.println(TAG + ": BeanConsumer has terminated");
	}
}