package dda.cqrs.orchestrator.dao;

import dda.cqrs.orchestrator.model.Request;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class RequestDAO {
    private static final String INSERT_QUERY =
            "insert into request (id, person_id, stock_code, stock_count, request_date)" +
                    " values (:id, :personId, :stockCode, :stockCount, :requestDate)";

    private static final String DELETE_QUERY = "delete from request where id = :requestId";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RequestDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Request request) {
        jdbcTemplate.update(INSERT_QUERY, new BeanPropertySqlParameterSource(request));
    }

    public void delete(UUID requestId) {
        jdbcTemplate.update(DELETE_QUERY, Map.of("requestId", requestId));
    }

    public Request getRequest(UUID requestId) {
        // todo
        return null;
    }
}
