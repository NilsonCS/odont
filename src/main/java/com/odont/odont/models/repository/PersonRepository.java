package com.odont.odont.models.repository;

import com.odont.odont.models.entity.PersonEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByAuthCode(String authCode);
}
