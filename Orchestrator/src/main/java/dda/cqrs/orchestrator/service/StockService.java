package dda.cqrs.orchestrator.service;

import dda.cqrs.orchestrator.model.StockInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    private final RestTemplate restTemplate;
    private final String serviceUrl;

    public StockService(RestTemplate restTemplate,
                        @Value("${service.stock.url}") String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl.endsWith("/") ? serviceUrl : serviceUrl + "/";
    }

    public StockInfo getStockInfo(String code) {
        return restTemplate.getForObject(serviceUrl + "api/v1/info/" + code, StockInfo.class);
    }


}
