package com.odont.odont.bot;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dto.PersonDto;
import com.odont.odont.models.entity.MaterialsEntity;
import com.odont.odont.models.entity.PersonEntity;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class MainBot extends TelegramLongPollingBot {

    IPersonDao personDao;
    PersonDto personDto;
    IMaterialsDao iMaterialsDao;
    ITreatmentDao iTreatmentDao;

    public MainBot(IPersonDao personDao, ITreatmentDao iTreatmentDao, IMaterialsDao iMaterialsDao) {
        this.personDao = personDao;
        this.iTreatmentDao = iTreatmentDao;
        this.iMaterialsDao = iMaterialsDao;
    }

    @Override
    public void onUpdateReceived(Update update){
        System.out.println(update);

        if(update.hasMessage() && update.getMessage().hasText()){

            MaterialsEntity materialsEntity = iMaterialsDao.findById((long) 1).get();

            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Materiales desde BBDD: "+materialsEntity+ "\n");
            System.out.println(materialsEntity);

            try{
                this.execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
/*
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);

        if (update.hasMessage() && update.getMessage().hasText()) {

            PersonEntity personEntity = personDao.findById((long) 1).get();
            //MaterialsEntitya materialsEntitya = iMaterialsDao.findById((long)3).get();

            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Persona desde BBDD: " + personEntity + "\n" + " Materiales desde BBDD:" );
                   // .setText("Persona desde BBDD: " + personDto);
            System.out.println(personEntity);


            try {
                this.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }

    }*/

    /* @@@@@@@@@@@@@@@@@@@@ Nilson Contreras @@@@@@@@@@@@@@@@@@@@@@@*/
//    @Override
//    public String getBotUsername() {
//        return "GatoscBot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "718088447:AAFnThJd7y3IjjcmWFFJadMLYhfrqlRkAbY";
//    }

    /* @@@@@@@@@@@@@@@@@@@@ Bacarreza Gadiel @@@@@@@@@@@@@@@@@@@@@@@*/
//     @Override
//    public String getBotUsername() {
//        return "GatoscBot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "718088447:AAFnThJd7y3IjjcmWFFJadMLYhfrqlRkAbY";
//    }

    /* @@@@@@@@@@@@@@@@@@@@ Vera Vania @@@@@@@@@@@@@@@@@@@@@@@*/

     @Override
    public String getBotUsername() {
        return "materiales_bot";
    }

    @Override
    public String getBotToken() {
        return "998435810:AAEScPMttRL_pnqy46amQfxg3bwvdWL6-Lo";
    }










    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }
}
