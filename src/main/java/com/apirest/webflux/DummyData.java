package com.apirest.webflux;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.apirest.webflux.document.PlayList;
import com.apirest.webflux.repository.PlayListRepository;

import reactor.core.publisher.Flux;

@Component
public class DummyData implements CommandLineRunner {

	private PlayListRepository playListRepository;
	
	DummyData(PlayListRepository playListRepository) {
		this.playListRepository = playListRepository;
	}
	
	public void run(String... args) throws Exception {
		
		playListRepository.deleAll()
				.thenMany(
						Flux.just("API REST Spring Boot", "Deploy de uma aplicação jana no IBM Cloud", "Java 8",
								"Github", "String Security", "Web Services RESTFULL", "Bean no String Framework")
								.map(nome -> new PlayList(UUID.randomUUID().toString(), nome))
								.flatMap(playListRepository::save))
				.subscribe(System.out::println);
	}
}
