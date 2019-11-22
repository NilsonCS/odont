package com.odont.odont.models.services;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.dto.MaterialsDto;
import com.odont.odont.models.entity.MaterialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialsServicelmpl{

    IMaterialsDao iMaterialsDao;


    @Autowired

    public MaterialsServicelmpl(IMaterialsDao iMaterialsDao) {
        this.iMaterialsDao = iMaterialsDao;
    }

    public List<MaterialsEntity> all(){
        List<MaterialsEntity> entityList = new ArrayList<>();
        List<MaterialsEntity> all = iMaterialsDao.findAll();
        for(MaterialsEntity entity: all){
            entityList.add(entity);
        }
        return entityList;
    }
}
