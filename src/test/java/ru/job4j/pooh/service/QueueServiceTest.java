package ru.job4j.pooh.service;

import org.junit.Test;
import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import static org.junit.Assert.*;

public class QueueServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegal() {
        QueueService service = new QueueService();
        Req req = Req.of(" ");
        service.process(req);
    }

    @Test
    public void whenPost() {
        QueueService service = new QueueService();
        Req req = Req.of("POST /queue/weather -d \"temperature=18\"");
        Resp resp = service.process(req);
        assertEquals(resp.text(), "temperature=18");
    }
    @Test
    public void whenPostAndGet() {
        QueueService service = new QueueService();
        Req req = Req.of("POST /queue/weather -d \"temperature=18\"");
        service.process(req);
        Req req1 = Req.of("GET /queue/weather");
        Resp resp = service.process(req1);
        assertEquals(resp.text(), "temperature=18");
    }

    @Test
    public void whenGet() {
        QueueService service = new QueueService();
        Req req1 = Req.of("GET /queue/weather");
        Resp resp = service.process(req1);
        assertNull(resp.text());
    }
}