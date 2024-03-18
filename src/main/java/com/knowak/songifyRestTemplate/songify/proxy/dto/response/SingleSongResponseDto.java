package com.knowak.songifyRestTemplate.songify.proxy.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.knowak.songifyRestTemplate.songify.model.SongEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SingleSongResponseDto(SongEntity song) {
}
