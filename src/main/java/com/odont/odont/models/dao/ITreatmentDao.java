package com.odont.odont.models.dao;

import com.odont.odont.models.entity.TreatmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITreatmentDao extends CrudRepository<TreatmentEntity, Long> {

}
