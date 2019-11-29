package com.odont.odont.bot;

import com.odont.odont.bl.BotBl;
import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import sun.applet.Main;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {

//    IPersonDao personDao;
//    IMaterialsDao iMaterialsDao;
//    ITreatmentDao iTreatmentDao;
//   MainBot mainBot;
//    @Autowired
//    public BotInitializator(BotBl botBl, IPersonDao personDao, ITreatmentDao iTreatmentDao, IMaterialsDao iMaterialsDao) {
//        this.personDao = personDao;
//        this.iTreatmentDao = iTreatmentDao;
//        this.iMaterialsDao = iMaterialsDao;
//        this.botBl = botBl;
//
//    }

    public BotInitializator() {

    }

    BotBl botBl;

//    @PostConstruct
//    public void init() {
//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//
//        try {
//            telegramBotsApi.registerBot(new MainBot(personDao, iTreatmentDao,iMaterialsDao));
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(botBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



}
