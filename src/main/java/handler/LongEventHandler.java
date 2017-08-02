package handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import event.LongEvent;

import java.awt.*;

/**
 * Author:shenqin
 * version: V1.0
 * Date: 2017/7/12
 * Time: 13:45
 * Description:事件处理器
 */
public class LongEventHandler implements EventHandler<LongEvent>,WorkHandler<LongEvent> {

   /**
    * 单线程事件处理
     * Called when a publisher has published an event to the {@link RingBuffer}
     *
     * @param event      published to the {@link RingBuffer}
     * @param sequence   of the event being processed
     * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link RingBuffer}
     * @throws Exception if the EventHandler would like the exception handled further up the chain.
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        Thread.sleep(1000);
//        for(int i=0;i<3;i++) {
            System.out.println("ba" + event.getValue());

//        }
    }

    /**
     * 多线程事件处理
     * Callback to indicate a unit of work needs to be processed.
     *
     * @param event published to the {@link RingBuffer}
     * @throws Exception if the {@link WorkHandler} would like the exception handled further up the chain.
     */
    @Override
    public void onEvent(LongEvent event) throws Exception {
        Thread.sleep(1000);
//        for(int i=0;i<3;i++) {
            System.out.println("sa" + event.getValue());
//            Thread.sleep(2000);
//        }
    }
}
