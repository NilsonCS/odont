package com.odont.odont.bot;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dto.PersonDto;

import com.odont.odont.models.entity.MaterialsEntity;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

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

    private long chatId;
    private User user;

    @Override
    public void onUpdateReceived(Update update){


        if(update.hasMessage() && update.getMessage().hasText()){
            System.out.println(update.getMessage().getFrom().getFirstName()+ ": " +update.getMessage().getText());

                switch (update.getMessage().getText()){
                    case "/start":
                        chatId = update.getMessage().getFrom().getId();
                        user = update.getMessage().getFrom();
                        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
                        sendMessage.setText("Bienvenido!!!!"+"\t" + update.getMessage().getFrom().getFirstName()+"\n"+
                        "\n" +"A: Si desea ver el menu escriba /menu" );

                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "/menu":
                        chatId = update.getMessage().getFrom().getId();
                        user = update.getMessage().getFrom();
                        SendMessage sendMessage1 = new SendMessage().setChatId(update.getMessage().getChatId());
                        sendMessage1.setText("El menu para los Materiales es:" );
                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        rowInline.add(new InlineKeyboardButton().setText("Agregar").setCallbackData("agregar"));
                        rowInline.add(new InlineKeyboardButton().setText("Editar").setCallbackData("editar"));
                        rowInline.add(new InlineKeyboardButton().setText("Eliminar").setCallbackData("eliminar"));
                        rowInline.add(new InlineKeyboardButton().setText("Lista").setCallbackData("lista"));



                        rowsInline.add(rowInline);
                        markupInline.setKeyboard(rowsInline);
                        sendMessage1.setReplyMarkup(markupInline);
                        try {
                            execute(sendMessage1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
            }
        }else if(update.hasCallbackQuery()){
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            String caso= "";
            switch (call_data){
                case"agregar":
                    System.out.println("Apreto Agregar");
                    caso = "Ingrese el nombre del curso a crear: ";
                    EditMessageText new_messageCrearCurso = new EditMessageText()
                            .setChatId(chat_id)
                            .setMessageId(toIntExact(message_id))
                            .setText(caso);
                    try {
                        execute(new_messageCrearCurso);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "editar":

                    System.out.println("Apreto editar");
                    break;

                case "eliminar":
                    System.out.println("Apreto eliminar");
                    break;

                case "lista":
                    System.out.println("Apreto Lista");
                    MaterialsEntity materialsEntity = iMaterialsDao.findById((long)1).get();
                    SendMessage message = new SendMessage()
                            .setChatId(chatId)
                            .setText("Materiales desde la Base de Datos"+materialsEntity);
                    System.out.println(materialsEntity);
                    try {
                        this.execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
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
