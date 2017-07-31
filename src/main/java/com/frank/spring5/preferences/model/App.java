package com.frank.spring5.preferences.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class App {

	private @Id String id;
	
	private @Field("creator") String creator;
	
	private @Field("description") String description;
	
	private List<BlockContainer> containers;
	
	@JsonCreator
	public App(@JsonProperty("id") String id, @JsonProperty("creator")  String creator, 
			@JsonProperty("description") String description, @JsonProperty("containers") List<BlockContainer> containers) {
		super();
		this.id = id;
		this.creator = creator;
		this.description = description;
		this.containers = containers;
	}

	public String getId() {
		return id;
	}

	public String getCreator() {
		return creator;
	}

	public String getDescription() {
		return description;
	}

	public List<BlockContainer> getContainers() {
		return containers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("App [id=");
		builder.append(id);
		builder.append(", creatorId=");
		builder.append(creator);
		builder.append(", description=");
		builder.append(description);
		builder.append(", containers=");
		builder.append(containers);
		builder.append("]");
		return builder.toString();
	}
	
	
}
