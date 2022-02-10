package dda.cqrs.payment.service;

import dda.cqrs.payment.event.AccountChanged;
import dda.cqrs.payment.event.BalanceRequestCanceled;
import dda.cqrs.payment.event.RequestCreated;
import dda.cqrs.payment.event.StocksRequestCanceled;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {

    private final PaymentDao paymentDao;

    public EventHandler(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public void listenRequestCreated(RequestCreated event) {
        paymentDao.insert(event);
    }

    public void listenBalanceRequestCanceled(BalanceRequestCanceled event) {
        paymentDao.updateStatus(event.getRequestId(), "Отказ: " + event.getReason());
    }

    public void listenStocksRequestCanceled(StocksRequestCanceled event) {
        paymentDao.updateStatus(event.getRequestId(), "Отказ: " + event.getReason());
    }

    public void listenAccountChanged(AccountChanged event) {
        paymentDao.updateStatus(event.getRequestId(), "Исполнен на сумму " + event.getSum().toPlainString());
    }

}
