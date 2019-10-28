package com.odont.odont.bot;

import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dto.PersonDto;
import com.odont.odont.models.entity.PersonEntity;
import com.odont.odont.models.services.IPersonService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class MainBot extends TelegramLongPollingBot {

    IPersonDao personDao;
    PersonDto personDto;

    public MainBot(IPersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            PersonEntity personEntity = personDao.findById((long) 1).get();
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Persona desde BBDD: " + personEntity);
                   // .setText("Persona desde BBDD: " + personDto);
            System.out.println(personEntity);

            try {
                this.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public String getBotUsername() {
        return "GatoscBot";
    }

    @Override
    public String getBotToken() {
        return "718088447:AAFnThJd7y3IjjcmWFFJadMLYhfrqlRkAbY";
    }


    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }
}
