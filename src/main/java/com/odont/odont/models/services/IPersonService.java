package com.odont.odont.models.services;

import com.odont.odont.models.entity.PersonEntity;

import java.util.List;

public interface IPersonService {

    public List<PersonEntity> findAll();
}
