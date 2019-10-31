package com.odont.odont.controllers;

import com.odont.odont.models.entity.MaterialsEntitya;
import com.odont.odont.models.entity.PersonEntity;
import com.odont.odont.models.services.IMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping ("/api")
public class MaterialsRestController{

    @Autowired
    private IMaterialsService iMaterialsService;


    @GetMapping("/persons")
    public List<MaterialsEntitya> index(){
        return iMaterialsService.findAll();

    }

    @GetMapping ("/materials/{idMaterials}")
    public MaterialsEntitya show(@PathVariable Long idMaterials) {
        return  iMaterialsService.findById(idMaterials);
    }

    @PostMapping("/materials")
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialsEntitya create(@RequestBody MaterialsEntitya materialsEntitya){
        return iMaterialsService.save(materialsEntitya);
    }

    @PutMapping("/materials/{idMaterials}") // para modificar
    //@ResponseStatus(HttpStatus.CREATED)
    public MaterialsEntitya update(@RequestBody MaterialsEntitya materialsEntitya, @PathVariable Long idMaterials) {
        MaterialsEntitya materialentitya = iMaterialsService.findById(idMaterials);

        materialentitya.setMaterialName(materialentitya.getMaterialName());
        materialentitya.setPriceIn(materialentitya.getPriceIn());
        materialentitya.setPriceOut(materialentitya.getPriceOut());
        materialentitya.setDateIn(materialentitya.getDateIn());
        materialentitya.setDateOut(materialentitya.getDateOut());
        materialentitya.setCreatedAt(materialentitya.getCreatedAt());
        materialentitya.setUpdateAt(materialentitya.getUpdateAt());
        materialentitya.setDeleteAt(materialentitya.getDeleteAt());
        return iMaterialsService.save(materialentitya);
    }

    @DeleteMapping("/materials/{idMaterials}")
    // @ResponseStatus(HttpStatus.CREATED.NO_CONTENT)
    public void delete(@PathVariable Long idMaterials){
        iMaterialsService.delete(idMaterials);
    }

}

