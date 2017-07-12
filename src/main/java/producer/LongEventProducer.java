package producer;

import com.lmax.disruptor.RingBuffer;
import event.LongEvent;

/**
 * Author:shenqin
 * version: V1.0
 * Date: 2017/7/12
 * Time: 13:49
 * Description:
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String str){
        long sequence=ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);// for the sequence
            event.setValue(Long.valueOf(str));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
