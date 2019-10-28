package com.odont.odont.controllers;

import com.odont.odont.models.entity.PersonEntity;
import com.odont.odont.models.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping ("/api")
public class PersonRestController {

    @Autowired
    private IPersonService personService;


    @GetMapping("/persons")
    public List<PersonEntity> index(){
        return personService.findAll();

    }
}
