package dda.cqrs.account.service;

import dda.cqrs.account.exception.SumNotExistException;
import dda.cqrs.account.model.command.ChangeAccount;
import dda.cqrs.account.model.command.CheckAccount;
import dda.cqrs.account.model.event.StocksRequestCanceled;
import org.springframework.stereotype.Component;

@Component
public class AccountCommandHandler {

    private final EventSender eventSender;
    private final AccountService accountService;

    public AccountCommandHandler(EventSender eventSender, AccountService accountService) {
        this.eventSender = eventSender;
        this.accountService = accountService;
    }

    //Приняли команду проверить аккаунт
    public void checkAccount(CheckAccount command) {
        try {
            //Проверяем счета
            accountService.createBalanceRequest(command.getRequestId(), command.getPersonId(), command.getSum());
            //Если все гут, то отправляем обратно что средства на аккаунте заблокированы
            eventSender.sendRequestConfirmed(command.getRequestId(), command.getSum());
        } catch (SumNotExistException exc) {
            exc.printStackTrace();
            eventSender.sendRequestRejected(command.getRequestId(), command.getSum());
        }
    }

    public void changeAccount(ChangeAccount command) {
        accountService.executeBalanceRequest(command.getRequestId(), command.getPersonId(), command.getSum());
        eventSender.sendAccountChanged(command.getRequestId(), command.getPersonId(), command.getSum());
    }

    public void cancelBalanceRequest(StocksRequestCanceled event) {
        accountService.cancelBalanceRequest(event.getRequestId());
    }


}
