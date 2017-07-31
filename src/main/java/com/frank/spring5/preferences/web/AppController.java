package com.frank.spring5.preferences.web;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.frank.spring5.preferences.model.App;
import com.frank.spring5.preferences.model.BlockContainer;
import com.frank.spring5.preferences.repo.ReactiveAppRepository;
import com.frank.spring5.preferences.repo.ReactiveBlockContainerRepository;

import reactor.core.publisher.Mono;

@RestController
public class AppController {

	@Autowired 
	private ReactiveAppRepository reactiveAppRepository;
	
	@Autowired
	private ReactiveBlockContainerRepository reactiveBlockContainerRepository;
	
	@GetMapping("/app/{id}")
	public Mono<App> getApp(@PathVariable String id) {
		return reactiveAppRepository.findById(id);
	}
	
	@PostMapping("/app")
	public Mono<App> saveApp(@RequestBody Mono<App> appStream) throws InterruptedException, ExecutionException {
		// FIXME: è il modo corretto di procedere?
		return reactiveAppRepository
                .save(appStream.toFuture().get());
	}
	
	@PutMapping("/app/{appId}/block")
	public Mono<Long> updateBlock(@PathVariable("appId") String appId, @RequestBody Mono<BlockContainer> containerStream) 
			throws InterruptedException, ExecutionException {		
		// FIXME: è il modo corretto di procedere?
		System.out.println("appId " + appId);
		
		BlockContainer block = containerStream.toFuture().get();
		System.out.println("block: " + block);
		return reactiveBlockContainerRepository.updateBlock(appId, 
				block);
	} 
}
