package ru.sidey383.imageweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class MapDataHandler implements HttpHandler {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try(InputStream out = exchange.getRequestBody()) {

            MapWebData data = mapper.readValue(out.readAllBytes(), MapWebData.class);
            System.out.println("size: " + data.getWidth() + " " + data.getHeigh());
            System.out.println("name: " + data.getName());
            for(int x = 0; x < data.getWidth(); x++)
                for(int y = 0; y < data.getWidth(); y++)
                    System.out.printf("data len on %d %d : %d%n", x, y, data.getMaps()[x][y].length);
        } catch (Exception e) {
            exchange.sendResponseHeaders(403,0);
        }
        exchange.sendResponseHeaders(202,0);
        exchange.close();
    }
}
