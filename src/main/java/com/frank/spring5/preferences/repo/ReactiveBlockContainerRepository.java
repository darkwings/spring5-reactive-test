package com.frank.spring5.preferences.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.frank.spring5.preferences.model.App;
import com.frank.spring5.preferences.model.BlockContainer;

import reactor.core.publisher.Mono;

@Repository
public class ReactiveBlockContainerRepository {

	@Autowired 
	private ReactiveMongoTemplate reactiveMongoTemplate;
	
	public Mono<Long> updateBlock(String appId, BlockContainer block) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(appId).
				and("containers.id").is(block.getId()));
		
		Update update = new Update();
		update.set("containers.$", block);
		
		return reactiveMongoTemplate.updateFirst(query, update, App.class).
				map(updR -> updR.getMatchedCount());
	}
}
