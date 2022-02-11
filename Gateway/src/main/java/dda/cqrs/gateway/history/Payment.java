package dda.cqrs.gateway.history;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.UUID;

@ApiModel(description = "Платёж")
public class Payment {

    @ApiModelProperty("Идентификатор запроса")
    private UUID requestId;

    @ApiModelProperty("Код ценной бумаги")
    private String stockCode;

    @ApiModelProperty("Количество бумаг для покупки")
    private int stockCount;

    @ApiModelProperty("Дата формирования запроса")
    private LocalDateTime requestDate;

    @ApiModelProperty("Статус платежа")
    private String status;

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
