package com.odont.odont.controllers;

import com.odont.odont.models.entity.TreatmentEntity;
import com.odont.odont.models.services.ITreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
