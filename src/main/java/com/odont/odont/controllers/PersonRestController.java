package com.odont.odont.controllers;

import ch.qos.logback.core.net.server.Client;
import com.odont.odont.models.entity.PersonEntity;
import com.odont.odont.models.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping ("/persons/{personId}")   // es necesario q "person id" sea el mismo que del parametro
    public PersonEntity show(@PathVariable Long personId) {
        return  personService.findById(personId);
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonEntity create(@RequestBody PersonEntity personEntity){
        return personService.save(personEntity);
    }

    @PutMapping("/person/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public PersonEntity update(@RequestBody PersonEntity personEntity, @PathVariable Long person_id) {
        PersonEntity personActual = personService.findById(person_id);

        personActual.setFirstName(personEntity.getFirstName());
        personActual.setSecondName(personEntity.getSecondName());
        personActual.setThirdName(personEntity.getThirdName());
        return personService.save(personActual);
    }

    @DeleteMapping("/persons/{id}")
   // @ResponseStatus(HttpStatus.CREATED(200).NO_CONTENT)
    public void delete(@PathVariable Long id){
        personService.delete(id);
    }

}
