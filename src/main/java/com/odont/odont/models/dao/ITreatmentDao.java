package com.odont.odont.models.dao;

import com.odont.odont.models.entity.TreatmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ITreatmentDao extends CrudRepository<TreatmentEntity, Long> {
}