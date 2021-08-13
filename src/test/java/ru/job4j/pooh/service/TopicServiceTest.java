package ru.job4j.pooh.service;

import org.junit.Test;
import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import static org.junit.Assert.*;

public class TopicServiceTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenIllegal() {
        TopicService service = new TopicService();
        Req req = Req.of(" ");
        service.process(req);
    }

    @Test
    public void whenPost() {
        TopicService service = new TopicService();
        Req req = Req.of("POST /topic/weather -d \"temperature=18\"");
        Resp resp = service.process(req);
        assertEquals(resp.text(), "temperature=18");
    }
    @Test
    public void whenPostAndGet() {
        TopicService service = new TopicService();
        Req req = Req.of("POST /topic/weather -d \"temperature=18\"");
        service.process(req);
        Req req1 = Req.of("GET /topic/weather/1");
        Resp resp = service.process(req1);
        assertEquals(resp.text(), "temperature=18");
    }

    @Test
    public void whenGet() {
        TopicService service = new TopicService();
        Req req1 = Req.of("GET /topic/weather/1");
        Resp resp = service.process(req1);
        assertNull(resp.text());
    }
}