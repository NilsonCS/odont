package com.odont.odont.bot;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {

    IPersonDao personDao;
    IMaterialsDao iMaterialsDao;
    ITreatmentDao iTreatmentDao;
    @Autowired
    public BotInitializator(IPersonDao personDao, IMaterialsDao iMaterialsDao, ITreatmentDao iTreatmentDao) {
        this.personDao = personDao;
        this.iMaterialsDao = iMaterialsDao;
        this.iTreatmentDao = iTreatmentDao;
    }

    public BotInitializator() {

    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(personDao,iMaterialsDao, iTreatmentDao));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
