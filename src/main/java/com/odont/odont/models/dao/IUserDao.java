package com.odont.odont.models.dao;

import com.odont.odont.models.entity.CpUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<CpUserEntity,Integer> {

    CpUserEntity findByBotUserId(String botUserId);
}
