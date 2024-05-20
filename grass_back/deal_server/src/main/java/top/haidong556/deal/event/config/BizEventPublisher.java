package top.haidong556.deal.event.config;

import org.springframework.stereotype.Component;
import top.haidong556.deal.event.BizBaseEvent;
import top.haidong556.deal.event.BizBaseEventListener;
import top.haidong556.deal.event.BizTopic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
@Component
public class BizEventPublisher implements BizEventEngine {

        /**
         * 异步执行器。也系统需要自行定义线程池
         */
        private Executor bizListenerExecutor;

        /**
         * 是否异步，默认为false
         */
        private boolean async;

        /**
         * 订阅端 KEY是TOPIC，VALUES是监听器集合
         */
        private Map<BizTopic, List<BizBaseEventListener>> bizTopicListMap = new HashMap<>(16);

        public void setAsync(boolean async) {
            this.async = async;
        }


    @Override
    public boolean publishEvent(BizBaseEvent event) {
        BizTopic topic=event.getTopic();
        List<BizBaseEventListener> listeners=bizTopicListMap.get(topic);
        if(listeners==null)
            return false;
        for(BizBaseEventListener l:listeners){
            if(l.isListeningEvent(event)){
                if (async) {
                    bizListenerExecutor.execute(l);
                } else {
                    l.doEvent();
                }

            }
        }
        return true;

}
}
