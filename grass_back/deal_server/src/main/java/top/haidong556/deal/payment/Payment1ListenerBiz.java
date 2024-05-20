package top.haidong556.deal.payment;

import top.haidong556.deal.event.BizBaseEventListener;
import top.haidong556.deal.event.PaymentEvent1Biz;

public class Payment1ListenerBiz extends BizBaseEventListener<PaymentEvent1Biz> {
    public Payment1ListenerBiz() {
        super(PaymentEvent1Biz.class);
    }

}
