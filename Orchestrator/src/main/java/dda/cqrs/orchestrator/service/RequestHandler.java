package dda.cqrs.orchestrator.service;

import dda.cqrs.orchestrator.dao.RequestDAO;
import dda.cqrs.orchestrator.model.Request;
import dda.cqrs.orchestrator.model.command.CreateRequest;
import dda.cqrs.orchestrator.model.event.AccountChanged;
import dda.cqrs.orchestrator.model.event.AccountChecked;
import dda.cqrs.orchestrator.model.event.StocksBought;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequestHandler {

    private final RequestDAO requestDao;
    private final CommandSender commandSender;
    private final StockService stockService;

    public RequestHandler(RequestDAO requestDao, CommandSender commandSender, StockService stockService) {
        this.requestDao = requestDao;
        this.commandSender = commandSender;
        this.stockService = stockService;
    }

    //Создается на запрос
    public void createRequest(CreateRequest command) {
        //Запрос инфы по акциям
        var stock = stockService.getStockInfo(command.getStockCode());

        var request = Request.from(command);
        //Сохраняем запрос
        requestDao.insert(request);
        //Уведомление о событии
        commandSender.requestCreated(request);
        //Кидаем в брокер сообщений проверить аккаунт - listenAccountChecked см. ниже
        commandSender.checkAccount(command.getId(), command.getPersonId(),
                stock.getPrice().multiply(BigDecimal.valueOf(command.getStockCount())));
    }

    //Создается по приходу событию проверка аккаунта
    public void listenAccountChecked(AccountChecked event) {
        //Из базы берем данные по запросу
        var req = requestDao.getRequest(event.getRequestId());
        //Отправляем запрос в брокер сообщений на покупку акций - listenStocksBought см. ниже
        commandSender.buyStock(event.getRequestId(), req.getStockCode(), req.getStockCount(), event.getSum());
    }

    //Создается по приходу событию покупка акций
    public void listenStocksBought(StocksBought event) {
        //Из базы берем данные по запросу
        var req = requestDao.getRequest(event.getRequestId());
        //Отправляем запрос в брокер сообщений изменение аккаунта - listenAccountChanged см. ниже
        commandSender.changeAccount(event.getRequestId(), req.getPersonId(), event.getSum());
    }

    //Создается по приходу событию изменение аккаунта
    public void listenAccountChanged(AccountChanged event) {
        //Удаляем из базы данных инфу о запросе
        requestDao.delete(event.getRequestId());
    }

}
