package com.odont.odont.models.services;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.entity.MaterialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialsServicelmpl{

    @Autowired
    private IMaterialsDao iMaterialsDao;

    public List<MaterialsEntity> listAll(){
        return iMaterialsDao.findAll();
    }

    public void save (MaterialsEntity materialsEntity){
        iMaterialsDao.save(materialsEntity);
    }

    public MaterialsEntity get(Long idmaterials){
        return iMaterialsDao.findById(idmaterials).get();
    }

    public void delete(Long idmaterials){

        iMaterialsDao.deleteById(idmaterials);
    }
}
