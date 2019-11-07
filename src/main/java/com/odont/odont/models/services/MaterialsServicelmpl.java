package com.odont.odont.models.services;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.entity.MaterialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MaterialsServicelmpl implements IMaterialsService{

    @Autowired
    private IMaterialsDao iMaterialsDao;

    @Override
    @Transactional(readOnly =  true)
    public List<MaterialsEntity>findAll(){
        return (List<MaterialsEntity>)iMaterialsDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)

    public MaterialsEntity findById(Long idmaterials){
        return iMaterialsDao.findById(idmaterials).orElse(null);
    }
    @Override
    @Transactional
    public MaterialsEntity save(MaterialsEntity materialsEntity){
        return iMaterialsDao.save(materialsEntity);
    }

    @Override
    @Transactional
    public void delete(Long id){
        iMaterialsDao.deleteById(id);
    }
}
