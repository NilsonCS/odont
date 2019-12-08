package com.odont.odont.bl;


import com.odont.odont.models.dao.IUserDao;
import com.odont.odont.models.entity.CpUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class UserBl {

    IUserDao iUserDao;

    public UserBl(){
        iUserDao = null;
    }

    @Autowired
    public UserBl(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    public CpUserEntity findPersonById(Integer pk) {
        Optional<CpUserEntity> optional = this.iUserDao.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }

    public CpUserEntity findUserByTelegramUserId(Update update){
        CpUserEntity cpUser = iUserDao.findByBotUserId(update.getMessage().getFrom().getId().toString());
        return cpUser;
    }
    /*public List<UserDto> findAllPeople() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (CpUser cpUser:cpUserRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            userDtoList.add(new UserDto(cpUser));
        }
        return userDtoList;
    }*/






}
