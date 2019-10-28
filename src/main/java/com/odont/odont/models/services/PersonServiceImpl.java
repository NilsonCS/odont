package com.odont.odont.models.services;

import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonDao personDao;
    @Override
    @Transactional(readOnly = true)
    public List<PersonEntity> findAll() {
        return (List<PersonEntity>) personDao.findAll();
    }
}
