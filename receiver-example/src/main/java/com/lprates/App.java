package com.lprates;

import com.lprates.bean.BeanConsumer;
import com.lprates.monitor.TheReceiverDummy;
import com.lprates.sender.Sender;

public class App {
	public static final int SENDERS = 1; // 5 senders
	public static final int TIMEOUT = 10000; // 20 seconds

	public static void main(String[] args) throws Exception {
		TheReceiverDummy receiver = new TheReceiverDummy();
		
		BeanConsumer beanConsumer = new  BeanConsumer(receiver);
		Sender sender1 = new Sender(receiver);
		Sender sender2 = new Sender(receiver);
		Sender sender3 = new Sender(receiver);
		
		new Thread(beanConsumer).start();
		new Thread(sender1).start();
		new Thread(sender2).start();
		new Thread(sender3).start();
				
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < TIMEOUT);
		
		beanConsumer.terminate();
		sender1.terminate();
		sender2.terminate();
		sender3.terminate();
	}
}
