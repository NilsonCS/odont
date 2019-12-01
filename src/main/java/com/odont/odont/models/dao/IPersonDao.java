package com.odont.odont.models.dao;

import com.odont.odont.models.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPersonDao extends CrudRepository  < PersonEntity, Integer> {

    List<PersonEntity> findAllByStatus(int status);
}
