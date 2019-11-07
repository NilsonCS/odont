package com.odont.odont.models.services;

import com.odont.odont.models.entity.MaterialsEntity;
import java.util.List;

public interface IMaterialsService {

    public List<MaterialsEntity> findAll();

    public MaterialsEntity findById(Long idmaterials);

    public MaterialsEntity save (MaterialsEntity materialsEntity);

    public void delete(Long id);

}
