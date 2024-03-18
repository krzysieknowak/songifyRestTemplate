package com.knowak.songifyRestTemplate;

import com.knowak.songifyRestTemplate.songify.service.SongifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SongifyRestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongifyRestTemplateApplication.class, args);
	}
	@Autowired
	SongifyService service;
	@EventListener(ApplicationStartedEvent.class)
	public void test(){
		service.fetchAllSongsFromSongifyHost();
		service.fetchSongFromSongifyHostById();
//		service.postSongToSongify();
		service.deleteSongFromSongify();
		service.putSongInSongify();
	}

}
