package br.com.carlettisolucoes.endpoint;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import br.com.carlettisolucoes.quote.Quote;
import br.com.carlettisolucoes.quote.QuoteDecoder;
import br.com.carlettisolucoes.quote.QuoteEncoder;

@ServerEndpoint(value = "/quote/{user}", decoders = QuoteDecoder.class, encoders = QuoteEncoder.class)
public class ExchangeEndpoint {

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) throws IOException, EncodeException {
        System.out.println("Open connection... " + user);
        this.session = session;
        EndpointManager.endpoints.add(this);
        EndpointManager.users.put(session.getId(), user);
        System.out.println("User add: " + user);
    }

    @OnMessage
    public void onMessage(Session session, Quote quote) throws IOException, EncodeException {
        System.out.println("Receiving quote: " + quote);
        String user = EndpointManager.users.get(session.getId());
        quote.setUser(user);
        quote.setPrice(quote.getPrice());
        EndpointManager.broadcast(quote);
        System.out.println("Quote broadcasted.");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String user = EndpointManager.users.get(session.getId());
        EndpointManager.endpoints.remove(this);
        System.out.println("User left: " + user);
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

}