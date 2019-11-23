package com.odont.odont.controllers;

import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.entity.TreatmentEntity;
import com.odont.odont.models.exception.ResourceNotFoundException;
import com.odont.odont.models.services.ITreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TreatmentRestController {
    @Autowired
    private ITreatmentDao treatmentDao;
    @GetMapping("/treatment")
    public List<TreatmentEntity> getAllTreatments() {
        return treatmentDao.findAll();
    }

    @GetMapping("/treatment/{id}")
    public ResponseEntity<TreatmentEntity> getTreatmentById(@PathVariable(value = "id") Long treatmentId)
            throws ResourceNotFoundException {

        TreatmentEntity treatmentEntity = treatmentDao
                .findById(treatmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found on :: " + treatmentId));
        return ResponseEntity.ok().body(treatmentEntity);
    }
    @PostMapping("/treatment")
    public TreatmentEntity createTreatment(@Valid @RequestBody TreatmentEntity treatmentEntity) {
        return treatmentDao.save(treatmentEntity);
    }

    @PutMapping("/treatment/{id}")
    public ResponseEntity<TreatmentEntity> updateTreatment(
            @PathVariable(value = "id") Long treatmentId, @Valid @RequestBody TreatmentEntity entity)
            throws ResourceNotFoundException {
        TreatmentEntity treatment = treatmentDao
                .findById(treatmentId)
                .orElseThrow(() -> new ResourceNotFoundException("treatment " + treatmentId + " not found"));

        treatment.setNameTreatment(entity.getNameTreatment());
        treatment.setCostTreatment(entity.getCostTreatment());
        treatment.setDuration(entity.getDuration());

        final TreatmentEntity updatedTreatment = treatmentDao.save(treatment);
        return ResponseEntity.ok(updatedTreatment);
    }
    @DeleteMapping("/treatment/{id}")
    public Map<String, Boolean> deleteTreatment(@PathVariable(value = "treatmentId") Long treatmentId) throws Exception {
        TreatmentEntity car = treatmentDao
                .findById(treatmentId)
                .orElseThrow(() -> new ResourceNotFoundException("treatment " + treatmentId + " not found"));

        treatmentDao.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
//@CrossOrigin(origins = {"http://localhost:8080"})
//@RestController
//@RequestMapping("/api")
//public class TreatmentRestController {
//    @Autowired
//    private ITreatmentService iTreatmentService;
//    @GetMapping("/treatment")
//    public List<TreatmentEntity> index(){
//        return iTreatmentService.findAll();
//    }
//    @GetMapping("/treatment/{treatmentId}")
//    public TreatmentEntity show(@PathVariable Long treatmentId){return iTreatmentService.findById(treatmentId);}
//    @PostMapping("/treatment")
//    @ResponseStatus(HttpStatus.CREATED)
//    public TreatmentEntity create(@RequestBody TreatmentEntity treatmentEntity){
//        return iTreatmentService.save(treatmentEntity);
//    }
//    @PutMapping("/treatment/{treatmentId}")
//    public TreatmentEntity update(@RequestBody TreatmentEntity treatmentEntity, @PathVariable Long treatmentId){
//        TreatmentEntity treatmentEntity1 = iTreatmentService.findById(treatmentId);
//        treatmentEntity1.setNameTreatment(treatmentEntity.getNameTreatment());
//        treatmentEntity1.setCostTreatment(treatmentEntity.getCostTreatment());
//        treatmentEntity1.setDuration(treatmentEntity.getDuration());
//        return iTreatmentService.save(treatmentEntity1);
//
//    }
//    @DeleteMapping("/treatment/{treatmentId}")
//    public void delete (@PathVariable Long treatmentId){iTreatmentService.delete(treatmentId);}
//
//}
