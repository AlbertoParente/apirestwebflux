package com.apirest.webflux.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.services.PlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PlayListController {

	@Autowired
	PlayListService playListService;
	
	@GetMapping(value="/playlist")
	public Flux<PlayList> getPlayList(){
		return playListService.findAll();
	}
	
	@GetMapping(value="/playlist/{id}")
	public Mono<PlayList> getPlayListId(@PathVariable String id) {
		return playListService.findById(id);
	}
	
	@PostMapping(value="/playlist")
	public Mono<PlayList> save(@RequestBody PlayList playList) {
		return playListService.save(playList);
	}
	
	@GetMapping(value="/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, PlayList>> getPlayListBtEvents() {
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
		Flux<PlayList> events = playListService.findAll();
		System.out.println("Passou aqui");
		return Flux.zip(interval, events);
	}
}