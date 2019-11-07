package com.odont.odont.models.dto;

import com.odont.odont.models.entity.PersonEntity;


public class PersonDto {
    private Long personId;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String firstSurname;
    private String secondSurname;
    private String thirdSurname;

    public PersonDto() {
    }

    public PersonDto(PersonEntity personDao) {
        this.firstName = personDao.getFirstName();
        this.secondName = personDao.getSecondName();
        this.thirdName = personDao.getThirdSurname();
        this.firstSurname = personDao.getFirstSurname();
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getThirdSurname() {
        return thirdSurname;
    }

    public void setThirdSurname(String thirdSurname) {
        this.thirdSurname = thirdSurname;
    }
}

