package com.odont.odont.controllers;
import com.odont.odont.models.entity.MaterialsEntitya;
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


    @GetMapping("/materials")
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

    @PutMapping("/materials/{idMaterials}")
    //@ResponseStatus(HttpStatus.CREATED)
    public MaterialsEntitya update(@RequestBody MaterialsEntitya materialsEntitya, @PathVariable Long idMaterials) {
        MaterialsEntitya materialentitya = iMaterialsService.findById(idMaterials);

        materialentitya.setMaterialName(materialsEntitya.getMaterialName());
        materialentitya.setPriceIn(materialsEntitya.getPriceIn());
        materialentitya.setPriceOut(materialsEntitya.getPriceOut());
        materialentitya.setDateIn(materialsEntitya.getDateIn());
        materialentitya.setDateOut(materialsEntitya.getDateOut());
        materialentitya.setCreatedAt(materialsEntitya.getCreatedAt());
        materialentitya.setUpdateAt(materialsEntitya.getUpdateAt());
        materialentitya.setDeleteAt(materialsEntitya.getDeleteAt());
        return iMaterialsService.save(materialentitya);
    }

    @DeleteMapping("/materials/{idMaterials}")
    public void delete(@PathVariable Long idMaterials){
        iMaterialsService.delete(idMaterials);
    }

}

