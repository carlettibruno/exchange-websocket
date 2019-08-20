package br.com.carlettisolucoes.endpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;

import br.com.carlettisolucoes.quote.Quote;

public class EndpointManager {

    public static final Set<ExchangeEndpoint> endpoints = new CopyOnWriteArraySet<>();
    public static HashMap<String, String> users = new HashMap<>();

    public static void broadcast(Quote quote) throws IOException, EncodeException {
        endpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.getSession().getBasicRemote().sendObject(quote);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}