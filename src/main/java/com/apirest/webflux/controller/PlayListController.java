package com.apirest.webflux.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.services.PlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import java.util.concurrent.TimeUnit;

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
	
	@GetMapping(value="/playlist/events"/ produces MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, PlayList>> getPlayListBtEvents() {
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds)
		Flux<PlayList> events = playListService.findAll();
		System.out.println("Passou aqui");
		return Fluz.zip(interval, events)
				
				
	}
	
	
	
	
}
