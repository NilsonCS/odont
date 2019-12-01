package com.odont.odont.bl;

import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dto.AddressDto;
import com.odont.odont.models.dto.PersonDto;
import com.odont.odont.models.dto.Status;
import com.odont.odont.models.entity.AddressEntity;
import com.odont.odont.models.entity.CpPersonAddress;
import com.odont.odont.models.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerBl {
    IPersonDao iPersonDao;

    @Autowired
    public CustomerBl(IPersonDao cpPersonDao) {
        this.iPersonDao = cpPersonDao;
    }

    public PersonEntity findPersonById(Integer pk) {
        Optional<PersonEntity> optional = this.iPersonDao.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Otra alternativa podría ser: crear una nueva persona con valores por defecto y retornar este nuevo objeto
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }

    public List<PersonDto> findAllPeople() {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (PersonEntity cpPerson:iPersonDao.findAllByStatus(Status.ACTIVE.getStatus())) {
            personDtoList.add(new PersonDto(cpPerson));
        }
        return personDtoList;
    }

    public List<PersonDto> findAllPeopleWithAddress() {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (PersonEntity cpPerson:iPersonDao.findAllByStatus(Status.ACTIVE.getStatus())) {
            PersonDto personDto = new PersonDto(cpPerson);
            List<AddressDto> addressDtoList = new ArrayList<>();
            List<CpPersonAddress> cpAddressList = cpPerson.getCpPersonAddressList();
            for(CpPersonAddress cpa : cpAddressList) {
                AddressEntity address = cpa.getAddressId();
                addressDtoList.add(new AddressDto(address));
            }
            personDto.setAddressList(addressDtoList);
            personDtoList.add(personDto);
        }
        return personDtoList;
    }
    /*

    public void saveOrder(UserTelegram user,  List<BookDto> books) {
        // mi logica de negocio
        // books -> CpBooks(Etities)

    }

     */
}