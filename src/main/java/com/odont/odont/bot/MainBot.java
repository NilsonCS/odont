package com.odont.odont.bot;

import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dto.PersonDto;

import com.odont.odont.models.entity.MaterialsEntity;
import com.odont.odont.models.entity.TreatmentEntity;
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

        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/menu")) {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("You send /menu");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Inventario").setCallbackData("Inventario"));
                rowInline.add(new InlineKeyboardButton().setText("Citas").setCallbackData("Citas"));
                rowInline.add(new InlineKeyboardButton().setText("Eliminar cita").setCallbackData("Eliminar"));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
            }
        } else if (update.hasCallbackQuery()) {

            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            if (call_data.equals("Inventario")) {//Vania
                SendMessage new_message = new SendMessage().setChatId(chat_id).setText("you send inventario");
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsLine = new ArrayList<>();
                List<InlineKeyboardButton> rowLine = new ArrayList<>();
                rowLine.add(new InlineKeyboardButton().setText("agregar").setCallbackData("agregar"));
                rowLine.add(new InlineKeyboardButton().setText("editar").setCallbackData("editar"));
                rowLine.add(new InlineKeyboardButton().setText("eliminar").setCallbackData("eliminar"));
                rowLine.add(new InlineKeyboardButton().setText("lista").setCallbackData("lista"));
                rowsLine.add(rowLine);
                markup.setKeyboard(rowsLine);
                new_message.setReplyMarkup(markup);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (update.hasCallbackQuery()){
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
            if (call_data.equals("Citas")) {//Gadiel
                SendMessage messageCita = new SendMessage().setChatId(chat_id).setText("you send citas");
                InlineKeyboardMarkup markupC = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsLine = new ArrayList<>();
                List<InlineKeyboardButton> rowLine = new ArrayList<>();
                rowLine.add(new InlineKeyboardButton().setText("nueva cita").setCallbackData("nueva"));
                rowLine.add(new InlineKeyboardButton().setText("modificar cita").setCallbackData("modificar"));
                rowLine.add(new InlineKeyboardButton().setText("eliminar cita").setCallbackData("eliminarC"));
                rowLine.add(new InlineKeyboardButton().setText("lista de citas").setCallbackData("listaC"));
                rowsLine.add(rowLine);
                markupC.setKeyboard(rowsLine);
                messageCita.setReplyMarkup(markupC);
                try {
                    execute(messageCita);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (update.hasCallbackQuery()){
                String caso= "";
                switch (call_data){
                    case"nueva":
                        System.out.println("Apreto nueva");
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
                    case "modificar":
                        System.out.println("Apreto editar");
                        break;

                    case "eliminarC":
                        System.out.println("Apreto eliminar");
                        break;

                    case "listaC":
                        System.out.println("Apreto Lista");
                        TreatmentEntity treatmentEntity = iTreatmentDao.findById((long)1).get();
                        SendMessage message = new SendMessage()
                                .setChatId(chatId)
                                .setText("Tratamientos de la Base de Datos"+treatmentEntity);
                        System.out.println(treatmentEntity);
                        try {
                            this.execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
            if (call_data.equals("Eliminar")) {//Nilson
                String answer = "Ingrese fecha y hora que desee eliminar";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    } //fin menu 



/*
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);

        if (update.hasMessage() && update.getMessage().hasText()) {

            //PersonEntity personEntity = personDao.findById((long) 1).get();
            //MaterialsEntitya materialsEntitya = iMaterialsDao.findById((long)3).get();
            TreatmentEntity treatmentEntity = iTreatmentDao.findById((long)3).get();
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Persona desde BBDD: " + treatmentEntity + "\n" + " Materiales desde BBDD:" );
                   // .setText("Persona desde BBDD: " + personDto);
            System.out.println(treatmentEntity);


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
     @Override
    public String getBotUsername() {
        return "CitasDentistabot";
    }

    @Override
    public String getBotToken() {
        return "971865743:AAHD6m_iDbNA03GkGwUFdReXG5z8Ttnb5UI";
    }

    /* @@@@@@@@@@@@@@@@@@@@ Vera Vania @@@@@@@@@@@@@@@@@@@@@@@*/

  /*   @Override
    public String getBotUsername() {
        return "materiales_bot";
    }

    @Override
    public String getBotToken() {
        return "998435810:AAEScPMttRL_pnqy46amQfxg3bwvdWL6-Lo";
    }*/

    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }
}
