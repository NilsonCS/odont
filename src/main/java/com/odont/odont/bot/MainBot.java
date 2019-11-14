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

      /*  if(update.hasMessage() && update.getMessage().hasText()){//inicio menu inventario
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
        }//

       */
         if (update.hasMessage() && update.getMessage().hasText()) { //inicio menu de citas
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/menu")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("You send /menu");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Reservar cita").setCallbackData("Reserva"));
                rowInline.add(new InlineKeyboardButton().setText("Modificar cita").setCallbackData("Modificar"));
                rowInline.add(new InlineKeyboardButton().setText("Eliminar cita").setCallbackData("Eliminar"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
            }
        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            if (call_data.equals("Reserva")) {
                String answer = "Ingrese fecha de reserva";
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
            if (call_data.equals("Modificar")) {
                String answer = "Ingrese nueva fecha y hora";
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
            if (call_data.equals("Eliminar")) {
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
    } //fin menu de citas


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
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        System.out.println("Se invoco clearWebhook");
    }*/
}
