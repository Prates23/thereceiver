package com.lprates.monitor;

import java.util.ArrayList;
import java.util.Collection;

import com.lprates.bean.DummyBean;
import com.lprates.buffer.DoubleBuffer;
import com.lprates.database.BeanSummarizeRepository;
import com.lprates.receiver.TheReceiver;

public class TheReceiverDummy extends TheReceiver {
	private DoubleBuffer<DummyBean> doubleBuffer;

	private BeanSummarizeRepository beanSummarizeRepository;
	
	private static final int FLUSH_LIMIT = 1000000000;
	private static final int SAVE_TIMEOUT = 5000; // 20 sec

	private boolean shutdown;
	private long lastSaveTime;

	public TheReceiverDummy() {
		doubleBuffer = new DoubleBuffer<DummyBean>();
		lastSaveTime = System.currentTimeMillis();
		shutdown = false;
		
		beanSummarizeRepository = new BeanSummarizeRepository();
	}

	@Override
	public void handleMessage(DummyBean message) {
		if (!shutdown) {
			this.doubleBuffer.getProducerQueue().add(message);
			synchronized (this) {
				notify();
			}
		}
	}

	public void waitFilling() {

		while (elapsedTime() < SAVE_TIMEOUT && doubleBuffer.getProducerQueue().size() < FLUSH_LIMIT) {
			try {
				synchronized (this) {
					wait();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void drainFill() {
		doubleBuffer.switchQueues();
		System.out.println("Consumer queue will be drained " + doubleBuffer.getConsumerQueue().size() + " elements");

		Collection<DummyBean> dummyBeans = new ArrayList<>();

		doubleBuffer.getConsumerQueue().drainTo(dummyBeans);

		lastSaveTime = System.currentTimeMillis();

		beanSummarizeRepository.save(dummyBeans);
		
		System.out.println("Drain completed");
	}

	private long elapsedTime() {
		return System.currentTimeMillis() - lastSaveTime;

	}
	
	public void shutdown() {
		shutdown = true;

		Collection<DummyBean> dummyBeans = new ArrayList<>();

		doubleBuffer.getConsumerQueue().drainTo(dummyBeans);
		beanSummarizeRepository.save(dummyBeans);
		doubleBuffer.getProducerQueue().drainTo(dummyBeans);
		beanSummarizeRepository.save(dummyBeans);
	}

}
