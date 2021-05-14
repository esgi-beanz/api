package fr.esgi.beanz.api.symbols;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.esgi.beanz.api.symbols.interfaces.QuoteResponseDTO;
import fr.esgi.beanz.api.symbols.interfaces.SearchSymbolsResponseDTO;

@Service
public class InstrumentsService {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final ObjectMapper mapper = new ObjectMapper();

    private String token;

    public InstrumentsService(@Value("${finnhub.token}") CharSequence token) {
        this.token = token.toString();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private <T> T get(String path, Class<T> type) throws ClientProtocolException, IOException {
        final var request = new HttpGet("https://finnhub.io/api/v1" + path + "&token=" + token);

        final var response = httpClient.execute(request);
        final var entity = response.getEntity();
        final var data = EntityUtils.toString(entity);

        return mapper.readValue(data, type);
    }

    public SearchSymbolsResponseDTO searchSymbols(String search) throws ClientProtocolException, IOException {
        return get("/search?q=" + search, SearchSymbolsResponseDTO.class);
    }

    public QuoteResponseDTO getSymbol(String symbol) throws ClientProtocolException, IOException {
        return get("/quote?symbol=" + symbol, QuoteResponseDTO.class);
    }
}
