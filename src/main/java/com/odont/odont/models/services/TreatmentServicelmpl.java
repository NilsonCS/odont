package com.odont.odont.models.services;

import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.entity.TreatmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TreatmentServicelmpl implements ITreatmentService{
    @Autowired
    private ITreatmentDao iTreatmentDao;
    @Override
    @Transactional(readOnly = true)
    public List<TreatmentEntity> findAll() {
        return (List<TreatmentEntity>) iTreatmentDao.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public TreatmentEntity findById(Long id_treatment) {
        return iTreatmentDao.findById(id_treatment).orElse(null);
    }
    @Override
    @Transactional
    public TreatmentEntity save(TreatmentEntity treatmentEntity) {
        return iTreatmentDao.save(treatmentEntity);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        iTreatmentDao.deleteById(id);
    }
}
