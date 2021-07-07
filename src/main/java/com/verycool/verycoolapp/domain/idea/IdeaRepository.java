package com.verycool.verycoolapp.domain.idea;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IdeaRepository extends CrudRepository<Idea, UUID> {
}
