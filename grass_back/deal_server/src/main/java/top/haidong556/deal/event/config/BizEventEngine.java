package top.haidong556.deal.event.config;

import top.haidong556.deal.event.BizBaseEvent;

public interface BizEventEngine {
    public boolean publishEvent(BizBaseEvent event);
}
