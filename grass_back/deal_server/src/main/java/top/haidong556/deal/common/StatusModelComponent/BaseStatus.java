package top.haidong556.deal.common.StatusModelComponent;

import top.haidong556.deal.paymentStatusModel.PaymentEvent;
import top.haidong556.deal.paymentStatusModel.PaymentStatus;

public interface BaseStatus<S extends BaseStatus,E extends BaseEvent> {
    static final StatusMachine<PaymentStatus,PaymentEvent> statusMachine=new StatusMachine<>();

    public S getTargetStatus(E event);


}
