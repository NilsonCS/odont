package com.odont.odont.models.services;

import com.odont.odont.models.entity.MaterialsEntitya;

import java.util.List;

public interface IMaterialsService {
    public List<MaterialsEntitya> findAll();
    public  MaterialsEntitya findById(Long idMaterials);
    public MaterialsEntitya save (MaterialsEntitya materialsEntitya);
    public void delete(Long id);

    /* public List<PersonEntity> findAll();

    public PersonEntity findById(Long personId);

    public PersonEntity save (PersonEntity personEntity);

    public void delete(Long id);*/

}
