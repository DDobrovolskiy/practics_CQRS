package dda.cqrs.orchestrator.dao;

import dda.cqrs.orchestrator.Application;
import dda.cqrs.orchestrator.model.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RequestDAOTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RequestDAO requestDao;

    @Test
    public void insert() {
        var req = new Request(UUID.randomUUID(), UUID.randomUUID(), "GMK", 10, LocalDateTime.now());
        requestDao.insert(req);

        var result = jdbcTemplate.queryForMap(" select * from request where id = ?", req.getId());

        assertTrue(result.size() > 0);
        assertEquals(req.getId(), result.get("id"));
        assertEquals(req.getPersonId(), result.get("person_id"));
        assertEquals(req.getStockCode(), result.get("stock_code"));
        assertEquals(String.valueOf(req.getStockCount()), result.get("stock_count").toString());
        assertNotNull(result.get("request_date"));

    }

    @Test
    public void delete() {
        var req = new Request(UUID.randomUUID(), UUID.randomUUID(), "GMK", 10, LocalDateTime.now());
        requestDao.insert(req);

        requestDao.delete(req.getId());

        var result = jdbcTemplate.queryForMap(" select * from request where id = ?", req.getId());
        //System.out.println(result);
        //assertEquals(0, result.size());
    }
}