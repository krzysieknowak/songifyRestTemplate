package com.knowak.songifyRestTemplate.songify.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class SongifyProxyServer {

    private final RestTemplate restTemplate;
    @Value("${songify.proxy.host}")
    private String host;
    @Value("${songify.proxy.port}")
    private int port;

    public SongifyProxyServer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String makeGetRequest(){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .port(port)
                .path("/shawn/songs");
        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", "123444");
        try{
            ResponseEntity<String> exchange = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String.class);
            return exchange.getBody();
        } catch (RestClientResponseException e) {
            log.error(e.getStatusText() + " " + e.getStatusCode().value());
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
