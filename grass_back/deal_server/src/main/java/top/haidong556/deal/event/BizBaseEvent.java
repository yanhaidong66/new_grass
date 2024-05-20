package top.haidong556.deal.event;

import java.util.EventObject;

public class BizBaseEvent extends EventObject {
    private final String bizId;
    private final BizTopic topic;
    private final Object eventSource;

    public BizBaseEvent(Object source, BizTopic topic, String bizId) {
        super(source);
        this.eventSource=source;
        this.topic=topic;
        this.bizId=bizId;
    }

    public String getBizId() {
        return bizId;
    }

    public BizTopic getTopic() {
        return topic;
    }

    public Object getEventSource() {
        return eventSource;
    }
}
