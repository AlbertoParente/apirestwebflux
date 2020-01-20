package com.apirest.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.services.PlayListService;

import reactor.core.publisher.Flux;

public class PlayListController {

	@Autowired
	PlayListService playListService;
	
	@GetMapping(value="/playlist")
	public Flux<PlayList> getPlayList(){
		return playListService.findAll();
	}
}
