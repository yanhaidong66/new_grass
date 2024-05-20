package top.haidong556.deal.common.StatusModelComponent;

import java.util.HashMap;
import java.util.Map;

public class StatusMachine<S extends BaseStatus,E extends BaseEvent> {

    private final Map<StatusEventPair<S,E>,S> statusEventMap = new HashMap<>();

    public void addStatusPair(S currentStatus,E event,S targetStatus){
        statusEventMap.put(new StatusEventPair<S,E>(currentStatus,event),targetStatus);
    }
    public S getTargetStatus(S currentStatus,E event){
        return statusEventMap.get(new StatusEventPair<>(currentStatus,event));
    }

}
