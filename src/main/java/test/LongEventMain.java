package test;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import event.LongEvent;
import factory.LongEventFactory;
import handler.LongEventHandler;
import producer.LongEventProducer;
import producer.LongEventProducerWithTranslator;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Author:shenqin
 * version: V1.0
 * Date: 2017/7/12
 * Time: 14:03
 * Description:
 */
public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

//        LongEventProducer producer = new LongEventProducer(ringBuffer);

        LongEventProducerWithTranslator producer=new LongEventProducerWithTranslator(ringBuffer);

        for (long i = 0; true; i++) {
            producer.onData(i+"");
            Thread.sleep(1000);
        }
    }
}
