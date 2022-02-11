package dda.cqrs.gateway.info;

import dda.cqrs.gateway.history.Payment;
import dda.cqrs.gateway.person.PersonInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Персональные данные по участнику")
public class PaymentsInfo {

    @ApiModelProperty("Информация об участнике")
    private PersonInfo person;

    @ApiModelProperty("Совершённые платежи (сделки)")
    private List<Payment> payments;

    public PaymentsInfo(PersonInfo person, List<Payment> payments) {
        this.person = person;
        this.payments = payments;
    }

    public PersonInfo getPerson() {
        return person;
    }

    public List<Payment> getPayments() {
        return payments;
    }
}