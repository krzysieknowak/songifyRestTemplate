package com.knowak.songifyRestTemplate.songify.proxy.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.knowak.songifyRestTemplate.songify.model.SongEntity;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AllSongsResponseDto(Map<String, SongEntity> songs) {

}
