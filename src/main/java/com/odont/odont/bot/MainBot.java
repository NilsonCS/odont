package com.odont.odont.bot;

import com.odont.odont.bl.BotBl;
import com.odont.odont.bl.PersonBl;
import com.odont.odont.bl.UserBl;
import com.odont.odont.models.dao.IMaterialsDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dto.MaterialsDto;
import com.odont.odont.models.dto.PersonDto;

import com.odont.odont.models.entity.CpUserEntity;
import com.odont.odont.models.entity.MaterialsEntity;
import com.odont.odont.models.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class MainBot extends TelegramLongPollingBot {


    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);
    //@ USO VANIA @@@@@@@@@
//    IPersonDao personDao;
//    PersonDto personDto;
//    MaterialsDto materialsDto;
//    IMaterialsDao iMaterialsDao;
//    ITreatmentDao iTreatmentDao;
//    private final static Logger LOGGER = Logger.getLogger(MainBot.class.getName());
//
//    public MainBot(IPersonDao personDao, ITreatmentDao iTreatmentDao, IMaterialsDao iMaterialsDao) {
//        this.personDao = personDao;
//        this.iTreatmentDao = iTreatmentDao;
//        this.iMaterialsDao = iMaterialsDao;
//    }
//
//    private long chatId;
//    private User user;
    BotBl botBl;
    private UserBl userBl;
    private PersonBl personBl;




    public static CpUserEntity cpUser = new CpUserEntity();
    public static PersonEntity cpPerson = new PersonEntity();

    public MainBot(BotBl customerBl) {
        this.botBl = customerBl;
    }






    // MMMMMMM no entiendo
//    @Autowired
//    public MainBot(BotBl botBl) {
//        this.botBl = botBl;
//    }


//   TODO FUNCIONA de NILSON no tocar
//    @Override
//    public void onUpdateReceived(Update update) {
//        System.out.println(update);
//        update.getMessage().getFrom().getId();
//
//        LOGGER.info("Recibiendo update {} ", update);
//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//
//            List<String> messages;
//            try {
//                //pato = botBl.processUpdate(update);
//                LOGGER.info("Ingresando al BL");
//                 messages = this.botBl.processUpdate(update);
//                LOGGER.info("SUCCESS ingrsando al BL");
//            } catch (Exception ex) {
//                LOGGER.warn("ERROR - process update", ex);
//                throw ex;
//            }
//
//
//            for(String messageText: messages) {
//                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//                        .setChatId(update.getMessage().getChatId())
//                        .setText(messageText);
//                try {
//                    this.execute(message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }



    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            responseConversation action = botBl.processUpdate(update);
            //switch moved to the response function
            response(action, update);
        }else if (update.hasCallbackQuery()) {
            responseConversation action = botBl.processUpdate(update);
            //switch moved to the response function
            response(action, update);
        }
    }







//                for(String Text: pato) {
//                    SendMessage patos = new SendMessage() // Create a SendMessage object with mandatory fields
//                            .setChatId(update.getMessage().getChatId())
//                            .setText(Text);
//                    try {
//                        this.execute(patos);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }





    private ReplyKeyboardMarkup createReplyKeyboardConfirmation() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Si");
        // Add the first row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("No");
        keyboard.add(row);

        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }
    //Here the user decides whether it will be a carpooler or a rider and creates a custom keyboard for it
    private ReplyKeyboardMarkup createReplyKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Carpooler");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Rider");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Corregir registro");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }
    private ReplyKeyboardMarkup createOkMenu(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("OK");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;

    }
    private ReplyKeyboardMarkup createReplyKeyboardCarpooler() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Registrar Vehículo");
        // Add the first row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Ver Vehículos");
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Registrar Viaje");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Ver Viajes");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Volver al Menú Principal");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup createReplyKeyboardRider() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text

        row.add("Buscar Paciente");
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Ver Peciente");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Eliminar Paciente");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Volver al Principio");
        // Add the second row to the keyboard
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }
    private ReplyKeyboardMarkup createReplyKeyboardOptions(List<String> options){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        for(String option: options){
            row.add(option);
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
        }
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup createReplyKeyboardTravels(List<String> options){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        for(String option: options){
            row.add(option);
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
        }
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    public void response(responseConversation action, Update update) {
        int conversation = action.getConversation();
        List<String> responses = new ArrayList<>();
        ReplyKeyboardMarkup rkm = null;
        switch (conversation) {
            //****************************************\\
            //Here is the initial registering\\
            //****************************************\\
            case 1:
                responses.add(" Odont");
                responses.add("registrate");
                responses.add("ingresa tu nombre");
                break;

        }
        for (String messageText : responses) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(messageText);
            if (rkm != null) {
                message.setReplyMarkup(rkm);
            } else {
                ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
                message.setReplyMarkup(keyboardMarkupRemove);
            }
            try {
                this.execute(message);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }




        // @@@@@@ USO DE VANIA
//@Override
//public void onUpdateReceived(Update update){
//
//
//    if(update.hasMessage() && update.getMessage().hasText()){
//        System.out.println(update.getMessage().getFrom().getFirstName()+ ": " +update.getMessage().getText());
//
//        switch (update.getMessage().getText()){
//            case "/start":
//                chatId = update.getMessage().getFrom().getId();
//                user = update.getMessage().getFrom();
//                SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
//                sendMessage.setText("Bienvenido!!!!"+"\t" + update.getMessage().getFrom().getFirstName()+"\n"+
//                        "\n" +"A: Si desea ver el menu escriba /menu" );
//
//                try {
//                    execute(sendMessage);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case "/menu":
//                chatId = update.getMessage().getFrom().getId();
//                user = update.getMessage().getFrom();
//                SendMessage sendMessage1 = new SendMessage().setChatId(update.getMessage().getChatId());
//                sendMessage1.setText("El menu para los Materiales es:" );
//                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//                List<InlineKeyboardButton> rowInline = new ArrayList<>();
//                rowInline.add(new InlineKeyboardButton().setText("Agregar").setCallbackData("agregar"));
//                rowInline.add(new InlineKeyboardButton().setText("Editar").setCallbackData("editar"));
//                rowInline.add(new InlineKeyboardButton().setText("Eliminar").setCallbackData("eliminar"));
//                rowInline.add(new InlineKeyboardButton().setText("Lista").setCallbackData("lista"));
//
//
//
//                rowsInline.add(rowInline);
//                markupInline.setKeyboard(rowsInline);
//                sendMessage1.setReplyMarkup(markupInline);
//                try {
//                    execute(sendMessage1);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
//    }else if(update.hasCallbackQuery()){
//        String call_data = update.getCallbackQuery().getData();
//        long message_id = update.getCallbackQuery().getMessage().getMessageId();
//        long chat_id = update.getCallbackQuery().getMessage().getChatId();
//        String caso= "";
//        switch (call_data){
//            case"agregar":
//                System.out.println("Apreto Agregar");
//                String text;
//                int option = 0;
//                switch (option){
//                    case 0:
//
//                        text = "Ingrese el codigo del material";
//                        sendMessage(chat_id,text);
//                        LOGGER.info("Codigo:");
//                        break;
//                    case 1:
//                        materialsDto.setCodeMaterials(update.getMessage().getText());
//                        text = "Ingrese el nombre del material";
//                        sendMessage(chat_id,text);
//                        LOGGER.info("Nombre");
//                        break;
//                    case 2:
//                        materialsDto.setName(update.getMessage().getText());
//                        text = "Ingrese el precio de entrada";
//                        sendMessage(chat_id,text);
//                        LOGGER.info("Precio entrada");
//                        break;
//                    case 3:
//                        materialsDto.setPriceIn(Double.valueOf(update.getMessage().getText()));
//                        text = "Ingrese el precio de salida";
//                        sendMessage(chat_id,text);
//                        LOGGER.info("Precio salida");
//                        break;
//                    case 4:
//                        materialsDto.setPriceOut(Double.valueOf(update.getMessage().getText()));
//                        text = "Ingrese la fecha de entrada";
//                        sendMessage(chat_id,text);
//                        LOGGER.info("Fecha entrada");
//                        break;
//                    case 5:
//                        materialsDto.setDateIn(Date.valueOf(update.getMessage().getText()));
//                        text = "Ingrese la fecha de salida";
//                        sendMessage(chat_id,text);
//                        LOGGER.info("Fecha salida");
//
//                    case 6:
//                        materialsDto.setDateOut(Date.valueOf(update.getMessage().getText()));
//                }

                // @@@@ Comentado vania y nilson @@@@
//                    caso = "Ingrese el nombre del curso a crear: ";
//                    EditMessageText new_messageCrearCurso = new EditMessageText()
//                            .setChatId(chat_id)
//                            .setMessageId(toIntExact(message_id))
//                            .setText(caso);
//                    try {
//                        execute(new_messageCrearCurso);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                break;
//            case "editar":
//
//                System.out.println("Apreto editar");
//                break;
//
//            case "eliminar":
//                System.out.println("Apreto eliminar");
//                break;
//
//            case "lista":
//                String list ="Codigo = "+ materialsDto.getCodeMaterials()+
//                        "Nombre = "+materialsDto.getName()+
//                        "Precio in = "+materialsDto.getPriceIn()+
//                        "Precio out = "+materialsDto.getPriceOut()+
//                        "Date in = "+materialsDto.getDateIn()+
//                        "Date out = "+materialsDto.getDateOut();
//                SendMessage sendMessage = new SendMessage()
//                        .setChatId(chatId)
//                        .setText("EL material registrado es: "+list );
//
//                System.out.println("Apreto Lista");
//                System.out.println(list);
//                LOGGER.info("Lista"+list);
//                    MaterialsEntity materialsEntity = iMaterialsDao.findById((long)1).get();
//                    SendMessage sendMessage = new SendMessage()
//                            .setChatId(chatId)
//                            .setText("Materiales desde la Base de Datos"+materialsEntity);
//                    System.out.println(materialsEntity);
//                    try {
//                        this.execute(sendMessage);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                break;
//        }
//    }
//}

//    public void sendMessage(long chat_id, String text){
//        SendMessage message = new SendMessage() // Create a message object object
//                .setChatId(chat_id)
//                .setText(text);
//        ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
//        message.setReplyMarkup(keyboardMarkupRemove);
//        try {
//            execute(message); // Sending our message object to user
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }


         /*if (update.hasMessage() && update.getMessage().hasText()) { //inicio menu de citas
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
        }*/
        // }fin menu de citas


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
                @Override
                public String getBotUsername () {
                    return "GatoscBot";
                }

                @Override
                public String getBotToken () {
                    return "718088447:AAFnThJd7y3IjjcmWFFJadMLYhfrqlRkAbY";
                }

        /* @@@@@@@@@@@@@@@@@@@@ Bacarreza Gadiel @@@@@@@@@@@@@@@@@@@@@@@*/
//     @Override
//    public String getBotUsername() {
//        return "CitasDentistabot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "971865743:AAHD6m_iDbNA03GkGwUFdReXG5z8Ttnb5UI";
//    }

        /* @@@@@@@@@@@@@@@@@@@@ Vera Vania @@@@@@@@@@@@@@@@@@@@@@@*/

//        @Override
//        public String getBotUsername () {
//            return "materiales_bot";
//        }
//
//        @Override
//        public String getBotToken () {
//            return "998435810:AAEScPMttRL_pnqy46amQfxg3bwvdWL6-Lo";
//        }

        @Override
        public void clearWebhook () throws TelegramApiRequestException {
            System.out.println("Se invoco clearWebhook");
        }


    }



