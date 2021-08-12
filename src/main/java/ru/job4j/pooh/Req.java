package ru.job4j.pooh;

public class Req {
    private final String method;
    private final String mode;
    private final String options;
    private final String text;

    private Req(String method, String mode, String options, String text) {
        this.method = method;
        this.mode = mode;
        this.options = options;
        this.text = text;
    }

    public static Req of(String content) {
        String[] parts = content.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException();
        }
        String[] options = parts[1].split("/");
        String opt = parts[1].substring(options[1].length() + 2);
        String text = "";
        for (int i = 2; i < parts.length; i++) {
            if (parts[i].equals("-d")) {
                for (int j = i + 1; j < parts.length; j++) {
                    text = content.split("\"")[1];
                }
                break;
            }
        }
        return new Req(parts[0], options[1], opt, text);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String text() {
        return text;
    }

    public String options() {
        return options;
    }
}
