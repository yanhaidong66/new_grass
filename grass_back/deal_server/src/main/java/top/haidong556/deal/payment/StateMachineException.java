package top.haidong556.deal.payment;

import top.haidong556.deal.paymentStatusModel.PaymentEvent;
import top.haidong556.deal.paymentStatusModel.PaymentStatus;

public class StateMachineException extends Throwable {
    public StateMachineException(PaymentStatus currentStatus, PaymentEvent event, String 状态转换失败) {
    }
}
