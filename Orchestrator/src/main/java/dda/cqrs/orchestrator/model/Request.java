package dda.cqrs.orchestrator.model;

import dda.cqrs.orchestrator.model.command.CreateRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class Request {

    private final UUID id;
    private final UUID personId;
    private final String stockCode;
    private final int stockCount;
    private final LocalDateTime requestDate;


    public Request(UUID id, UUID personId, String stockCode, int stockCount, LocalDateTime requestDate) {
        this.id = id;
        this.personId = personId;
        this.stockCode = stockCode;
        this.stockCount = stockCount;
        this.requestDate = requestDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPersonId() {
        return personId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getStockCount() {
        return stockCount;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public static Request from(CreateRequest command) {
        return new Request(command.getId(), command.getPersonId(), command.getStockCode(), command.getStockCount(),
                command.getRequestDate());
    }
}