package com.knowak.songifyRestTemplate.songify.proxy;

import com.knowak.songifyRestTemplate.songify.proxy.dto.request.SongRequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
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
    @Value("${songify.proxy.scheme}")
    private String scheme;
    @Value("${songify.proxy.port}")
    private int port;
    @Value("${songify.proxy.getById}")
    private int getById;
    @Value("${songify.proxy.deleteById}")
    private int deleteById;
    @Value("${songify.proxy.putById}")
    private int putById;

    public SongifyProxyServer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String makeGetRequest(){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path("/songs");
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("requestId", "123444");
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

    public String makeGetRequestById(){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path("/songs")
                .path("/" + getById);
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
    public String makePostRequest(){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path("/songs");
        SongRequestDto requestDto = new SongRequestDto("No reason", "Sum 41");
        HttpEntity<SongRequestDto> httpEntity = new HttpEntity<>(requestDto);
        try{
            ResponseEntity<String> exchange = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.POST,
                    httpEntity,
                    String.class);
            return exchange.getBody();
        } catch (RestClientResponseException e) {
            log.error(e.getStatusText() + " " + e.getStatusCode().value());
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
        return null;
    }
    public String makeDeleteRequestById(){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path("/songs")
                .path("/" + deleteById);
        try{
            ResponseEntity<String> exchange = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.DELETE,
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
    public String makePutRequestById(){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path("/songs")
                .path("/" + putById);
        SongRequestDto requestDto = new SongRequestDto("Unstoppable", "Sia");
        HttpEntity<SongRequestDto> httpEntity = new HttpEntity<>(requestDto);
        try{
            ResponseEntity<String> exchange = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.PUT,
                    httpEntity,
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
