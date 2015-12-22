package com.lprates.sender;

import com.lprates.bean.DummyBean;
import com.lprates.receiver.TheReceiver;

import java.util.Random;

public class Sender implements Runnable {
	private static final String TAG = Sender.class.getName();

    private TheReceiver receiver;
    private Random random;
    private boolean running;

    public Sender(TheReceiver receiver) {
        this.receiver = receiver;
        random = new Random(System.nanoTime());
    }

    public TheReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(TheReceiver receiver) {
        this.receiver = receiver;
    }

    public void terminate() {
        running = false;
    }

    public void run() {
        running = true;
       
        while(running) {
            DummyBean dummyBean = new DummyBean();
            dummyBean.setId(random.nextInt(10));
            dummyBean.setValue(1L);
            receiver.handleMessage(dummyBean);
            try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO AUTO-GENERATED CATCH BLOCK
				e.printStackTrace();
			}
        }
        System.out.println(TAG + ": Sender \"" + Thread.currentThread().getName() + "\" has exited running loop.");
    }
}
