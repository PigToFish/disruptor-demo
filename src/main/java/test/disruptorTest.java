package test;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import event.LongEvent;
import factory.LongEventFactory;
import handler.LongEventHandler;
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
public class disruptorTest {

    private static final int THREAD_NUM = 3;

    public static void main(String[] args) throws InterruptedException {
        // 触发 Consumer 的事件处理
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        EventFactory<LongEvent> factory = new LongEventFactory();
        // RingBuffer 大小，必须是 2 的 N 次方
        int bufferSize = 1024;

        //多个消费者
        LongEventHandler[] eventDisruptorConsumers = new LongEventHandler[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            eventDisruptorConsumers[i] = new LongEventHandler();
        }

        RingBuffer<LongEvent> ringBuffer = RingBuffer.createSingleProducer(factory, bufferSize);

        WorkerPool<LongEvent> workerPool =
                new WorkerPool<LongEvent>(ringBuffer, ringBuffer.newBarrier(),
                        new IgnoreExceptionHandler(), eventDisruptorConsumers);
        //将WorkPool的工作序列集设置为ringBuffer的追踪序列。
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        workerPool.start(executor);

//        LongEventProducer producer = new LongEventProducer(ringBuffer);

        LongEventProducerWithTranslator producer=new LongEventProducerWithTranslator(ringBuffer);

        for (long i = 0; i<6; i++) {
            System.out.println("事件 "+i);
            producer.onData(i+"");
//            Thread.sleep(1000);
        }

    }
}
