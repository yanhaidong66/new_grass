package top.haidong556.deal.payment;

import top.haidong556.deal.paymentStatusModel.PaymentEvent;
import top.haidong556.deal.paymentStatusModel.PaymentModel;

/**
 * 支付领域域服务
 */
public class PaymentDomainServiceImpl implements PaymentDomainService,Runnable {
    private  PaymentModel paymentModel=new PaymentModel();
    public void pay(){};

    /**
     * 支付结果通知
     */
//    public void notify(PaymentNotifyMessage message) {
//
//        try {
//
//            // 状态推进
//            paymentModel.transformStatusByEvent(PaymentEvent.valueOf(message.getEvent()));
//            savePaymentModel(paymentModel);
//            // 其它业务处理
//        	... ...
//        } catch (StateMachineException e) {
//            // 异常处理
//            ... ...
//        } catch (Exception e) {
//            // 异常处理
//            ... ...
//        }
//    }

    @Override
    public void run() {

    }
}