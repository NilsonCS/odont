package com.odont.odont.models.services;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.entity.MaterialsEntitya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MaterialsServiceImpl implements IMaterialsService {
    @Autowired
    private IMaterialsDao iMaterialsDao;

    @Override
    @Transactional(readOnly = true)
    public List<MaterialsEntitya> findAll() {
        return (List<MaterialsEntitya>) iMaterialsDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)

    public MaterialsEntitya findById(Long person_id) {
        return iMaterialsDao.findById(person_id).orElse(null);
    }

    @Override
    @Transactional

    public MaterialsEntitya save(MaterialsEntitya personEntity) {
        return iMaterialsDao.save(personEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iMaterialsDao.deleteById(id);
    }
}
