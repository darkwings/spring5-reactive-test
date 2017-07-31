package com.frank.spring5.preferences.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.frank.spring5.preferences.model.App;

@Repository
public interface ReactiveAppRepository extends ReactiveCrudRepository<App, String>{

	
}
