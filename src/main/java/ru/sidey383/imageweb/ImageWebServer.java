package ru.sidey383.imageweb;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URL;

public class ImageWebServer implements Closeable {

    HttpServer server;

    public ImageWebServer(int port, int backlog) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), backlog);
        startServer();
    }

    public ImageWebServer(int port, int backlog, HttpsConfigurator configurator) throws IOException {
        HttpsServer server = HttpsServer.create(new InetSocketAddress(port), backlog);
        this.server = server;
        server.setHttpsConfigurator(configurator);
        startServer();
    }

    private void startServer() throws IOException {
        server.createContext("/", getMainPage());
        server.createContext("/images.js", getImageScriptPage());
        server.createContext("/minecraftColors.js", getColorsScriptPage());
        server.createContext("/mapData", new MapDataHandler());
        server.setExecutor(null);
        server.start();
    }

    private HttpHandler getMainPage() throws IOException {
        return getPage("web/index.html");
    }

    private HttpHandler getImageScriptPage() throws IOException {
        return getPage("web/images.js");
    }

    private HttpHandler getColorsScriptPage() throws IOException {
        return getPage("web/minecraftColors.js");
    }

    private HttpHandler getPage(String name) throws IOException {
        URL resource = getClass().getClassLoader().getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            try(InputStream in = resource.openStream()) {
                return new StaticHttpPageHandler(new String(in.readAllBytes()));
            }
        }
    }

    @Override
    public void close() {
        if(server != null)
            server.stop(0);
    }
}
