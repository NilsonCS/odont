package com.odont.odont.models.dao;

import com.odont.odont.models.entity.MaterialsEntity;
import org.springframework.data.repository.CrudRepository;

public interface IMaterialsDao extends CrudRepository <MaterialsEntity,Long> {
}
