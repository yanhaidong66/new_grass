package top.haidong556.deal.paymentStatusModel;

import top.haidong556.deal.common.StatusModelComponent.BaseStatus;

public enum PaymentStatus implements BaseStatus<PaymentStatus,PaymentEvent> {
    INIT("INIT","初始化"),
    PAYING("PAYING","正在支付"),
    PAID("PAID","支付成功"),
    FAILED("FAILED","支付失败")
    ;
    private final String status;
    private final String description;

    static {
        statusMachine.addStatusPair(null,PaymentEvent.PAYMENT_CREATE,INIT);
        statusMachine.addStatusPair(INIT,PaymentEvent.PAYMENT_PROCESS,PAYING);
        statusMachine.addStatusPair(PAYING,PaymentEvent.PAYMENT_SUCCESS,PAID);
        statusMachine.addStatusPair(PAYING,PaymentEvent.PAYMENT_FAIL,FAILED);
    }
    private PaymentStatus(String s,String d){
        this.status=s;
        this.description=d;
    }

    public PaymentStatus getTargetStatus(PaymentEvent event){
        return statusMachine.getTargetStatus(this,event);
    }

}
