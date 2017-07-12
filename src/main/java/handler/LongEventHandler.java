package handler;

import com.lmax.disruptor.EventHandler;
import event.LongEvent;

/**
 * Author:shenqin
 * version: V1.0
 * Date: 2017/7/12
 * Time: 13:45
 * Description:事件处理器
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     * Called when a publisher has published an event to the {@link RingBuffer}
     *
     * @param event      published to the {@link RingBuffer}
     * @param sequence   of the event being processed
     * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link RingBuffer}
     * @throws Exception if the EventHandler would like the exception handled further up the chain.
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getValue());
    }
}
