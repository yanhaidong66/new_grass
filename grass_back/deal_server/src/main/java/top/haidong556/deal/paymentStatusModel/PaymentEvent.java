package top.haidong556.deal.paymentStatusModel;

import top.haidong556.deal.common.StatusModelComponent.BaseEvent;

public enum PaymentEvent implements BaseEvent {
    PAYMENT_CREATE("PAY_CREATE","支付创建"),
    PAYMENT_PROCESS("PAYMENT_PROCESS","支付中"),
    PAYMENT_SUCCESS("PAYMENT_SUCCESS","支付成功"),
    PAYMENT_FAIL("PAYMENT_PAIL","支付失败");

    private final String event;
    private final String description;
    private PaymentEvent(String e,String d){
        this.event=e;
        this.description=d;
    }
}
