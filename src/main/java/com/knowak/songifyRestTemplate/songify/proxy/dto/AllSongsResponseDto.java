package com.knowak.songifyRestTemplate.songify.proxy.dto;

import java.util.Map;

public record AllSongsResponseDto(Map<Integer,SingleSongResponseDto> songs) {
}
