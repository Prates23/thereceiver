package com.lprates.receiver;

import com.lprates.bean.DummyBean;

public abstract class TheReceiver {

	public abstract void handleMessage(DummyBean message);

	public void connect() {

	}
}
