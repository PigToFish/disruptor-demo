package producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import event.LongEvent;

/**
 * Author:shenqin
 * version: V1.0
 * Date: 2017/7/12
 * Time: 13:57
 * Description:
 */
public class LongEventProducerWithTranslator {

    private static final EventTranslatorOneArg<LongEvent, String> TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, String>() {
                public void translateTo(LongEvent event, long sequence, String str) {
                    event.setValue(Long.valueOf(str));
                }
            };

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String str) {
        ringBuffer.publishEvent(TRANSLATOR, str);
    }
}
