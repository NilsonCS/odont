package com.odont.odont.models.dao;

import com.odont.odont.models.entity.PatientEntity;
import com.odont.odont.models.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPatientDao extends JpaRepository<PatientEntity,Integer> {

   // @Query(value = "select * from restaurant where person_id=?1 order by chat_id desc LIMIT 1",nativeQuery = true)
    @Query(value = "select * from patient where person_id=?1 order by chat_id desc LIMIT 1",nativeQuery = true)

    public PersonEntity findPersonId(Integer person_id);
}
