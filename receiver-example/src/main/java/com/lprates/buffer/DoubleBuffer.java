package com.lprates.buffer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 */
public class DoubleBuffer<DummyBean>{
    private BlockingQueue<DummyBean>producerQueue;
    private BlockingQueue<DummyBean>consumerQueue;

    public DoubleBuffer() {
        producerQueue = new LinkedBlockingQueue<DummyBean>();
        consumerQueue = new LinkedBlockingQueue<DummyBean>();
    }

    /**
     * Method getProducerQueue.
     * @return BlockingQueue<DummyBean>
     */
    public BlockingQueue<DummyBean>getProducerQueue() {
        return producerQueue;
    }

    /**
     * Method getConsumerQueue.
     * @return BlockingQueue<DummyBean>
     */
    public BlockingQueue<DummyBean>getConsumerQueue() {
        return consumerQueue;
    }

    public synchronized void switchQueues() {
        BlockingQueue<DummyBean>temporaryQueue = producerQueue;
        producerQueue = consumerQueue;
        consumerQueue = temporaryQueue;
    }    
}
