package com.odont.odont.models.dao;

import com.odont.odont.models.entity.TreatmentEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.awt.*;
import java.util.List;

public interface ITreatmentDao extends JpaRepository<TreatmentEntity, Long> {
    List<TreatmentEntity> findAllByTreatmentId (int treatmentId);
    TreatmentEntity findAllByNameTreatment (String nameTreatment);
    TreatmentEntity findAllByCostTreatment (Double costTreatment);
    TreatmentEntity findAllByDuration (String duration);
}
