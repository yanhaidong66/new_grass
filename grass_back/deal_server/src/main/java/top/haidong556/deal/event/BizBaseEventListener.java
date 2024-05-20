package top.haidong556.deal.event;

import java.util.EventListener;

public class BizBaseEventListener<E extends BizBaseEvent> implements EventListener, Runnable {
    private Class<E> listeningEventClass;

    public BizBaseEventListener(Class<E> eventClass){
        this.listeningEventClass=eventClass;
    }

    public boolean isListeningEvent(BizBaseEvent event){
        return listeningEventClass.isInstance(event);
    }
    public void doEvent(){

    }

    @Override
    public void run() {
        doEvent();
    }
}
