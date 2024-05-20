package top.haidong556.deal.service.paymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.haidong556.deal.event.BizBaseEvent;
import top.haidong556.deal.event.BizTopic;
import top.haidong556.deal.event.config.BizEventPublisher;
import top.haidong556.deal.paymentStatusModel.PaymentEvent;
import top.haidong556.deal.paymentStatusModel.PaymentModel;

@Component
public class PaymentServiceImpl implements PaymentService {
    PaymentModel paymentModel;
    @Autowired
    BizEventPublisher bizEventPublisher;
    @Override
    public void pay() {
        bizEventPublisher.publishEvent(new BizBaseEvent(PaymentEvent.PAYMENT_CREATE, BizTopic.TOPIC_A,"23"));
    }


}
