package dda.cqrs.gateway.info;

import dda.cqrs.gateway.history.PaymentsRestClient;
import dda.cqrs.gateway.person.PersonRestClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentsInfoService {

    private final PaymentsRestClient paymentsRestClient;
    private final PersonRestClient personRestClient;

    public PaymentsInfoService(PaymentsRestClient paymentsRestClient, PersonRestClient personRestClient) {
        this.paymentsRestClient = paymentsRestClient;
        this.personRestClient = personRestClient;
    }

    public PaymentsInfo getPaymentHistory(UUID personId) {
        return new PaymentsInfo(personRestClient.getPersonInfo(personId),
                paymentsRestClient.getPayments(personId));
    }

}
