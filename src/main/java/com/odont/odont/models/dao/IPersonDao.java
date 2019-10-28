package com.odont.odont.models.dao;

import com.odont.odont.models.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDao extends CrudRepository  < PersonEntity, Long> {
}
