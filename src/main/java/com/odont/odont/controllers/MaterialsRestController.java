package com.odont.odont.controllers;


import com.odont.odont.models.entity.MaterialsEntity;
import com.odont.odont.models.services.IMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class MaterialsRestController {

    @Autowired
    private IMaterialsService iMaterialsService;

    @GetMapping("/materials")
    public List<MaterialsEntity> index(){

        return iMaterialsService.findAll();
    }

    @GetMapping("/materials/{idmaterials}")
    public MaterialsEntity show(@PathVariable Long idmaterials){
        return iMaterialsService.findById(idmaterials);
    }

    @PostMapping("/materials")
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialsEntity create(@RequestBody MaterialsEntity materialsEntity){

        return iMaterialsService.save(materialsEntity);
    }

    @PutMapping("/materials/{idmaterials}")
    public MaterialsEntity update(@RequestBody MaterialsEntity materialsEntity, @PathVariable Long idmaterials){

        MaterialsEntity materials = iMaterialsService.findById(idmaterials);

        materials.setName(materialsEntity.getName());
        materials.setPriceIn(materials.getPriceIn());
        materials.setPriceOut(materials.getPriceOut());
        materials.setDateIn(materials.getDateIn());
        materials.setDateOut(materials.getDateOut());
        return iMaterialsService.save(materials);
    }

    @DeleteMapping("/materials/{idmaterials}")
    public  void delect (@PathVariable Long idmaterials){
        iMaterialsService.delete(idmaterials);
    }
}
