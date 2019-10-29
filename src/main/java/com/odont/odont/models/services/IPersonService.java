package com.odont.odont.models.services;

import com.odont.odont.models.entity.PersonEntity;

import java.util.List;

public interface IPersonService {

    public List<PersonEntity> findAll();

    public PersonEntity findById(Long person_id);

    public PersonEntity save (PersonEntity personEntity);

    public void delete(Long id);
}
