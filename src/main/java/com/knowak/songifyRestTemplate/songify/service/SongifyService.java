package com.knowak.songifyRestTemplate.songify.service;

import com.knowak.songifyRestTemplate.songify.proxy.SongifyProxyServer;
import com.knowak.songifyRestTemplate.songify.proxy.dto.response.AllSongsResponseDto;
import com.knowak.songifyRestTemplate.songify.proxy.dto.response.SingleSongResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SongifyService {

    private final SongifyProxyServer songifyProxyServer;
    private final SongMapper songMapper;


    public SongifyService(SongifyProxyServer songifyProxyServer, SongMapper songMapper) {
        this.songifyProxyServer = songifyProxyServer;
        this.songMapper = songMapper;
    }

    public String fetchAllSongsFromSongifyHost(){
        String json = songifyProxyServer.makeGetRequest();
        if(json == null){
            log.error("Json is empty");
            return "";
        }
        AllSongsResponseDto responseDto = songMapper.mapJsonToAllSongsResponseDto(json);
        log.info("Songs fetched from Songify host: " + responseDto);
        return "Songs fetched successfully";
    }
    public String fetchSongFromSongifyHostById(){
        String json = songifyProxyServer.makeGetRequestById();
        if(json == null){
            log.error("Json is empty");
            return "";
        }
        SingleSongResponseDto responseDto = songMapper.mapJsonToSingleSongResponseDto(json);
        log.info("Song fetched from Songify host: " + responseDto);
        return "Song fetched successfully";
    }
    public void postSongToSongify(){
        String json = songifyProxyServer.makePostRequest();
        if(json != null){
            SingleSongResponseDto response = songMapper.mapJsonToSingleSongResponseDto(json);
            log.info("Added new song: " + response);
        }else{
            log.error("Cannot add song");
        }
    }
    public void deleteSongFromSongify(){
        String json = songifyProxyServer.makeDeleteRequestById();
        if(json==null){
            log.info("Cannot find song with this id or song is already deleted");
        }else{
            log.info("Song deleted");
        }

    }
    public void putSongInSongify(){
        String json = songifyProxyServer.makePutRequestById();
        if(json != null){
            SingleSongResponseDto response = songMapper.mapJsonToSingleSongResponseDto(json);
            log.info("Replaced with: " + response);
        }else{
            log.error("Cannot replace song");
        }
    }
}
