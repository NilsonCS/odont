package com.odont.odont.bl;


import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dto.PersonDto;
import com.odont.odont.models.dto.Status;
import com.odont.odont.models.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonBl {


IPersonDao iPersonDao;

    @Autowired
    public PersonBl(IPersonDao iPersonDao) {
        this.iPersonDao = iPersonDao;
    }

    public PersonEntity findRiderById(Integer pk){
        Optional<PersonEntity> optional = this.iPersonDao.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }


    public List<PersonDto> findAllPeople() {
        List<PersonDto> riderDtoList = new ArrayList<>();
        for (PersonEntity cpRiderEntity: iPersonDao.findAllByStatus(Status.ACTIVE.getStatus())) {
            riderDtoList.add(new PersonDto(cpRiderEntity));
        }
        return riderDtoList;
    }

    public PersonEntity findPersonById(Integer id){
        return iPersonDao.findById(id).get();
    }

}