package com.odont.odont.models.services;

import com.odont.odont.models.entity.TreatmentEntity;

import java.util.List;

public interface ITreatmentService {
    public List<TreatmentEntity> findAll();
    public  TreatmentEntity findById(Long idMaterials);
    public TreatmentEntity save (TreatmentEntity treatmentEntity);
    public void delete(Long id);
}
