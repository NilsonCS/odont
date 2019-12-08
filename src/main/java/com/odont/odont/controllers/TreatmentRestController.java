package com.odont.odont.controllers;

import com.odont.odont.bl.TreatmentBl;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dto.TreatmentDto;
import com.odont.odont.models.entity.TreatmentEntity;
import com.odont.odont.models.exception.ResourceNotFoundException;
import com.odont.odont.models.services.ITreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TreatmentRestController {
    private TreatmentBl treatmentBl;
    private ITreatmentDao treatmentDao;

    @Autowired
    public TreatmentRestController(TreatmentBl treatmentBl) {
        this.treatmentBl = treatmentBl;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<TreatmentDto> all(){
        List<TreatmentDto> treatmentDtoList= new ArrayList<>();
        for(TreatmentEntity treatmentEntity: treatmentDao.findAll()){
            treatmentDtoList.add(new TreatmentDto(treatmentEntity));
        }
        return treatmentDtoList;
    }


}
