package factory;

import com.lmax.disruptor.EventFactory;
import event.LongEvent;

/**
 * Author:shenqin
 * version: V1.0
 * Date: 2017/7/12
 * Time: 13:38
 * Description:创建事件
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
