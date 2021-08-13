package ru.job4j.pooh.service;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue =
            new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> users =
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
        String[] opt = req.options().split("/");
        if (opt.length < 2) {
            throw new IllegalArgumentException("Не указан id пользователя");
        }
        queue.putIfAbsent(opt[0], new ConcurrentLinkedQueue<>());
        String id = opt[opt.length - 1];
        String text = queue.get(opt[0]).poll();
        users.putIfAbsent(id, new ConcurrentLinkedQueue<>());
        if (text != null) {
            users.get(id).add(text);
        }
        return new Resp(text, 0);
    }
}
