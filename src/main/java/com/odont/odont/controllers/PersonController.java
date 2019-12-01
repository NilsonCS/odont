package com.odont.odont.controllers;

import com.odont.odont.bl.CustomerBl;
import com.odont.odont.models.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private CustomerBl customerBl;

    @Autowired
    public PersonController(CustomerBl customerBl) {
        this.customerBl = customerBl;
    }


    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PersonDto> all(@RequestParam(name = "includeAddress") boolean includeAddress) { // El valor por defecto sera false
        if (includeAddress) {
            return customerBl.findAllPeopleWithAddress();
        } else {
            return customerBl.findAllPeople();
        }
    }


}
