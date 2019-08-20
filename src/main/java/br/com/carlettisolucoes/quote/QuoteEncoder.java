package br.com.carlettisolucoes.quote;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class QuoteEncoder implements Encoder.Text<Quote> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Quote message) throws EncodeException {
        String json = gson.toJson(message);
        return json;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }

}