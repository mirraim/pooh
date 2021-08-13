package ru.job4j.pooh.service;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@ThreadSafe
public class QueueService implements Service {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if (req.method().equals("POST")) {
            return post(req);
        }
        if (req.method().equals("GET")) {
            return get(req);
        }
        throw new IllegalArgumentException();
    }

    private Resp post(Req req) {
        String key = req.options();
        queue.putIfAbsent(key, new ConcurrentLinkedQueue<>());
        queue.get(key).add(req.text());
        return new Resp(req.text(), 0);
    }

    private Resp get(Req req) {
        queue.putIfAbsent(req.options(), new ConcurrentLinkedQueue<>());
        return new Resp(queue.get(req.options()).poll(), 0);
    }
}
