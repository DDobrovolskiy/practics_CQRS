package dda.cqrs.gateway.info;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(description = "Информация о платежах")
@RestController
@RequestMapping(value = "/api/v1")
public class PaymentInfoController {

    private final PaymentsInfoService paymentsInfoService;

    public PaymentInfoController(PaymentsInfoService paymentsInfoService) {
        this.paymentsInfoService = paymentsInfoService;
    }


    @ApiOperation("История платежей")
    @GetMapping(value = "/{personId}/payment_history", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PaymentsInfo getPaymentStatistic(@PathVariable("personId") UUID personId) {
        return paymentsInfoService.getPaymentHistory(personId);
    }

}
