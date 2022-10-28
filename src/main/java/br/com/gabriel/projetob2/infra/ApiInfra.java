package br.com.gabriel.projetob2.infra;

import br.com.gabriel.projetob2.infra.exception.CallFailedException;
import br.com.gabriel.projetob2.infra.exception.JsonFormatException;
import br.com.gabriel.projetob2.model.Selic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class ApiInfra {

    private static List<Selic> buildSelic(HttpResponse<String> response) {
        List<Selic> pessoa;
        final var json = response.body();
        final var objectMapper = new ObjectMapper();
        try {
            pessoa = objectMapper.readValue(json, new TypeReference<List<Selic>>() {
            });
        } catch (JsonProcessingException e) {
            throw new JsonFormatException("Falha ao passar o json para objeto");
        }
        return pessoa;
    }

    public List<Selic> getApiSelic() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados?formato=json&dataInicial=01/01/2010&dataFinal=31/12/2022"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new CallFailedException("Falha ao se comunicar com a api do governo");
        }

        return buildSelic(response);

    }
}
