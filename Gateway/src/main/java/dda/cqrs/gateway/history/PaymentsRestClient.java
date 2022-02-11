package dda.cqrs.gateway.history;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentsRestClient {


    private final RestTemplate restTemplate;
    private final String serviceUrl;

    public PaymentsRestClient(RestTemplate restTemplate,
                              @Value("${service.payments.url}") String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    public List<Payment> getPayments(UUID personId) {
        return restTemplate.getForObject(serviceUrl + "api/v1/payments/" + personId, PaymentsList.class);
    }

}
