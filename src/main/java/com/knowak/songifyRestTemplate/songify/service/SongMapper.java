package com.knowak.songifyRestTemplate.songify.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowak.songifyRestTemplate.songify.model.SongEntity;
import com.knowak.songifyRestTemplate.songify.proxy.dto.request.SongRequestDto;
import com.knowak.songifyRestTemplate.songify.proxy.dto.response.AllSongsResponseDto;
import com.knowak.songifyRestTemplate.songify.proxy.dto.response.SingleSongResponseDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SongMapper {
    private final ObjectMapper objectMapper;
    Map<String,SongEntity> check = Map.of(
            "1", new SongEntity("TestName","TestArtist")
    );


    public SongMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    AllSongsResponseDto mapJsonToAllSongsResponseDto(String json){
        try {
            return objectMapper.readValue(json, AllSongsResponseDto.class);
        } catch (JsonProcessingException e) {
            return new AllSongsResponseDto(check);
        }
    }
    SingleSongResponseDto mapJsonToSingleSongResponseDto(String json){
        try {
            return objectMapper.readValue(json, SingleSongResponseDto.class);
        } catch (JsonProcessingException e) {
            return new SingleSongResponseDto(new SongEntity("empty", "empty"));
        }
    }
    SongRequestDto mapJsonToSongsRequestDto(String json){
        try {
            return objectMapper.readValue(json, SongRequestDto.class);
        } catch (JsonProcessingException e) {
            return new SongRequestDto("empty", "empty");
        }
    }
}
