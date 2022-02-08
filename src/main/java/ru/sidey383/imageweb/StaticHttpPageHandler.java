package ru.sidey383.imageweb;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class StaticHttpPageHandler implements HttpHandler {

    String data;

    public StaticHttpPageHandler(String data) {
        this.data = data;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, data.length());
        try(OutputStream os = exchange.getResponseBody()) {
            os.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
