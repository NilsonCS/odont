package com.odont.odont.controllers;


import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.entity.MaterialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/materials/")
public class MaterialsRestController {

    private final IMaterialsDao iMaterialsDao;

    @Autowired
    public MaterialsRestController(IMaterialsDao iMaterialsDao) {
        this.iMaterialsDao = iMaterialsDao;
    }

    @GetMapping("creat")
    public String showCreatMaterials(MaterialsEntity materialsEntity)
    {
        return "add-materials";
    }

    @GetMapping("list")
    public String showUpdateMaterials(Model model){
        model.addAttribute("materials", iMaterialsDao.findAll());
        return "index";
    }

    @PostMapping("add")
    public String addMaterials(@Valid MaterialsEntity materialsEntity, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-Materials";
        }
        iMaterialsDao.save(materialsEntity);
        return "List:";
    }

    @GetMapping("edit/{materialsid}")
    public String showUpdateMaterials(@PathVariable("materialsid")long id, Model model){

        MaterialsEntity materialsEntity = iMaterialsDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Materials: "+id));
        model.addAttribute("materials",materialsEntity);
        return "update-materials";
    }
    @PostMapping("update/{materialsid}")
    public String updateMaterials(@PathVariable("materialsid")long id,@Valid MaterialsEntity materialsEntity,BindingResult result, Model model){
        if(result.hasErrors()){
            materialsEntity.setIdmaterials(id);
            return "update-materials";
        }
        iMaterialsDao.save(materialsEntity);
        model.addAttribute("Materials",iMaterialsDao.findAll());
        return "index";
    }

    @GetMapping("delete/{materialsid}")
    public String deleteMaterials(@PathVariable("materialsid") long id, Model model) {
        MaterialsEntity materialsEntity = iMaterialsDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Materials: "+id));
        iMaterialsDao.delete(materialsEntity);
        model.addAttribute("materials", iMaterialsDao.findAll());
        return "index";
    }



//@CrossOrigin(origins = {"http://localhost:8080"})
//@RestController
//@RequestMapping("/api")
//public class MaterialsRestController {
//
//    @Autowired
//    private IMaterialsService iMaterialsService;
//
//    @GetMapping("/materials")
//    public List<MaterialsEntity> index(){
//
//        return iMaterialsService.findAll();
//    }
//
//    @GetMapping("/materials/{idmaterials}")
//    public MaterialsEntity show(@PathVariable Long idmaterials){
//        return iMaterialsService.findById(idmaterials);
//    }
//
//    @PostMapping("/materials")
//    @ResponseStatus(HttpStatus.CREATED)
//    public MaterialsEntity create(@RequestBody MaterialsEntity materialsEntity){
//
//        return iMaterialsService.save(materialsEntity);
//    }
//
//    @PutMapping("/materials/{idmaterials}")
//    public MaterialsEntity update(@RequestBody MaterialsEntity materialsEntity, @PathVariable Long idmaterials){
//
//        MaterialsEntity materials = iMaterialsService.findById(idmaterials);
//
//        materials.setName(materialsEntity.getName());
//        materials.setPriceIn(materials.getPriceIn());
//        materials.setPriceOut(materials.getPriceOut());
//        materials.setDateIn(materials.getDateIn());
//        materials.setDateOut(materials.getDateOut());
//        return iMaterialsService.save(materials);
//    }
//
//    @DeleteMapping("/materials/{idmaterials}")
//    public  void delect (@PathVariable Long idmaterials){
//        iMaterialsService.delete(idmaterials);
//    }

}
