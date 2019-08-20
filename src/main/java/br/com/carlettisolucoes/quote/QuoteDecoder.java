package br.com.carlettisolucoes.quote;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class QuoteDecoder implements Decoder.Text<Quote> {

    private static Gson gson = new Gson();

    @Override
    public Quote decode(String s) throws DecodeException {
        Quote quote = gson.fromJson(s, Quote.class);
        return quote;
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }

}