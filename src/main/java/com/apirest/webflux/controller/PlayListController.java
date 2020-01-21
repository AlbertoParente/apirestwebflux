package com.apirest.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.services.PlayListService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PlayListController {

	@Autowired
	PlayListService playListService;
	
	@GetMapping(value="/playlist")
	public Flux<PlayList> getPlayList(){
		return playListService.findAll();
	}
}
