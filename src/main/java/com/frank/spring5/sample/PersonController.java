package com.frank.spring5.sample;

import java.util.concurrent.ExecutionException;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {

	@Autowired
	private ReactivePersonRepository repo;

	public PersonController() {
	}

	@GetMapping("/people")
	public Flux<String> namesByLastname(@RequestParam String lastname) {

		Flux<Person> result = repo.findByLastname(lastname);
		return result.map(it -> it.getFirstname() + " " + it.getLastname());
	}
	
	@PostMapping("/person")
    public Mono<Person> create(@RequestBody Mono<Person> personStream) throws InterruptedException, ExecutionException {
        return repo
                .save(personStream.toFuture().get());
    }
}
