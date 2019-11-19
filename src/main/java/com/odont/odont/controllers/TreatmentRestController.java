package com.odont.odont.controllers;

import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.entity.TreatmentEntity;
import com.odont.odont.models.services.ITreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping({"/treatment"})
public class TreatmentRestController {
    private ITreatmentDao iTreatmentDao;
    TreatmentRestController(ITreatmentDao treatmentDao){
        this.iTreatmentDao = treatmentDao;
    }
    @GetMapping
    public List findAll(){
        return iTreatmentDao.findAll();
    }
    @GetMapping(path = {"/{treatmentId}"})
    public ResponseEntity<TreatmentEntity> findById(@PathVariable long id){
        return iTreatmentDao.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public TreatmentEntity treatmentEntity(@RequestBody TreatmentEntity entity){
        return iTreatmentDao.save(entity);
    }
    @PutMapping(value = "/{treatmentId}")
    public ResponseEntity<TreatmentEntity> update(@PathVariable("treatmentId") long id, @RequestBody TreatmentEntity entity){
        return iTreatmentDao.findById(id).map(record -> {
           record.setNameTreatment((entity.getNameTreatment()));
           record.setDuration(entity.getDuration());
           record.setCostTreatment(entity.getCostTreatment());
           TreatmentEntity update = iTreatmentDao.save(record);
           return ResponseEntity.ok().body(update);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(path = {"/{treatmentId}"})
    public ResponseEntity<?> delete(@PathVariable("treatmentId") long id){
        return iTreatmentDao.findById(id).map(record -> {
            iTreatmentDao.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
/*
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/api")
public class TreatmentRestController {
    @Autowired
    private ITreatmentService iTreatmentService;
    @GetMapping("/treatment")
    public List<TreatmentEntity> index(){
        return iTreatmentService.findAll();
    }
    @GetMapping("/treatment/{treatmentId}")
    public TreatmentEntity show(@PathVariable Long treatmentId){return iTreatmentService.findById(treatmentId);}
    @PostMapping("/treatment")
    @ResponseStatus(HttpStatus.CREATED)
    public TreatmentEntity create(@RequestBody TreatmentEntity treatmentEntity){
        return iTreatmentService.save(treatmentEntity);
    }
    @PutMapping("/treatment/{treatmentId}")
    public TreatmentEntity update(@RequestBody TreatmentEntity treatmentEntity, @PathVariable Long treatmentId){
        TreatmentEntity treatmentEntity1 = iTreatmentService.findById(treatmentId);
        treatmentEntity1.setNameTreatment(treatmentEntity.getNameTreatment());
        treatmentEntity1.setCostTreatment(treatmentEntity.getCostTreatment());
        treatmentEntity1.setDuration(treatmentEntity.getDuration());
        return iTreatmentService.save(treatmentEntity1);

    }
    @DeleteMapping("/treatment/{treatmentId}")
    public void delete (@PathVariable Long treatmentId){iTreatmentService.delete(treatmentId);}

}*/
