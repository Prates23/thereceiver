package com.lprates;

import com.lprates.bean.BeanConsumer;
import com.lprates.monitor.TheReceiverDummy;
import com.lprates.sender.Sender;

/**
 */
public class App {
	public static final int SENDERS = 1; // 5 senders
	public static final int TIMEOUT = 10000; // 20 seconds

	/**
	 * Method main.
	 * @param args String[]
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TheReceiverDummy receiver = new TheReceiverDummy();
		
		BeanConsumer beanConsumer = new  BeanConsumer(receiver);
		Sender sender1 = new Sender(receiver);
		Sender sender2 = new Sender(receiver);
		Sender sender3 = new Sender(receiver);
		
		Thread t1 = new Thread(beanConsumer);
		Thread t2 = new Thread(sender1);
		Thread t3 = new Thread(sender2);
		Thread t4 = new Thread(sender3);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
				
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < TIMEOUT);

		beanConsumer.terminate();
		sender1.terminate();
		sender2.terminate();
		sender3.terminate();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}
}
