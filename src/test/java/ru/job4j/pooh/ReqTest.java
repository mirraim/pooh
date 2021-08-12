package ru.job4j.pooh;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReqTest {

    @Test
    public void whenMethod() {
        Req req = Req.of("POST /queue/weather -d \"temperature=18\"");
        assertEquals(req.method(), "POST");
    }

    @Test
    public void whenText() {
        Req req = Req.of("POST /queue/weather -d \"temperature=18\"");
        assertEquals(req.text(), "temperature=18");
    }

    @Test
    public void whenMode() {
        Req req = Req.of("POST /queue/weather -d \"temperature=18\"");
        assertEquals(req.mode(), "queue");
    }

    @Test
    public void whenOptions() {
        Req req = Req.of("POST /queue/weather -d \"temperature=18\"");
        assertEquals(req.options(), "weather");
    }
}