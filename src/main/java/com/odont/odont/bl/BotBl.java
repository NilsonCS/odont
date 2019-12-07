package com.odont.odont.bl;

import com.odont.odont.models.dao.IChatDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dao.IUserDao;
import com.odont.odont.models.dto.Status;
import com.odont.odont.models.entity.CpChatEntity;
import com.odont.odont.models.entity.CpUserEntity;
import com.odont.odont.models.entity.PersonEntity;
import com.odont.odont.models.entity.TreatmentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    @Service
    public class BotBl {

        private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

        private IUserDao iUserDao;
        private IPersonDao iPersonDao;
        private IChatDao iChatDao;
        private ITreatmentDao treatmentDao;

        @Autowired
        public BotBl(IUserDao iUserDao, IPersonDao iPersonDao, IChatDao iChatDao, ITreatmentDao treatmentDao) {
            this.iUserDao = iUserDao;
            this.iPersonDao = iPersonDao;
            this.iChatDao = iChatDao;
            this.treatmentDao = treatmentDao;
        }

        public String guardarTratamientos (List<String> listaTratamientos){
            TreatmentEntity treatmentEntity = new TreatmentEntity();
            treatmentEntity.setNameTreatment(listaTratamientos.get(0));
            treatmentEntity.setCostTreatment(Double.parseDouble(listaTratamientos.get(1)));
            treatmentEntity.setDuration(listaTratamientos.get(2));
            treatmentDao.save(treatmentEntity);
            return "Se guardo correctamente";
        }

        public List<String> processUpdate(Update update) {
            List<String> result = new ArrayList<>();
            try {
                LOGGER.info("Ingresando a funcion processUpdate");
                int a = 1;
                LOGGER.info("Recibiendo update {} ", update);

                // Si es la primera vez pedir una imagen para su perfil
                if (a == 1) {
                    //if (initUser(update.getMessage().getFrom())) {
                    result.add("Bienvenido al bot primer inicio de sesion");
                } else {
                    result.add("Bienvenido segunda xxx vez");
                }
            } catch (Exception ex) {
                LOGGER.warn("ERROR en processUpdate", ex);
                throw ex;
            }

            return result;
        }
//        public void continueChatWithUserMessage(Update update, TreatmentEntity treatmentEntity, SendMessage sendMessage){
//                TreatmentEntity lastmessage = (TreatmentEntity) iChatDao.findLastChatByUserId(treatmentEntity.getTreatmentId());
//                String messageInput = update.getMessage().getText();
//                long treatmentId = update.getMessage().getChatId();
//                LOGGER.info("ultimo mensaje"+update.getMessage().getText());
//                SendMessage message = new SendMessage().setChatId(treatmentId).setText("default");
//
//                SendMessage responseMessage= new SendMessage();
//                String messageTextReceived = update.getMessage().getText();
//                LOGGER.info("ultimo mensaje "+update.getMessage().getText());
//                String response = "";
//
//                if(lastmessage == null){
//                    message.setChatId(treatmentId)
//                            .setText("DEFAULT por null");
//                }else{
//                    // agregar posibles respuestas a treatmentBl
//                    if (update.hasMessage() && update.getMessage().hasText()) { //inicio menu de citas
//            String message_text = update.getMessage().getText();
//            long chat_id = update.getMessage().getChatId();
//            if (update.getMessage().getText().equals("/menu")) {
//                SendMessage message = new SendMessage() // Create a message object object
//                        .setChatId(chat_id)
//                        .setText("You send /menu");
//                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//                List<InlineKeyboardButton> rowInline = new ArrayList<>();
//                rowInline.add(new InlineKeyboardButton().setText("Reservar cita").setCallbackData("Reserva"));
//                rowInline.add(new InlineKeyboardButton().setText("Modificar cita").setCallbackData("Modificar"));
//                rowInline.add(new InlineKeyboardButton().setText("Eliminar cita").setCallbackData("Eliminar"));
//                // Set the keyboard to the markup
//                rowsInline.add(rowInline);
//                // Add it to the message
//                markupInline.setKeyboard(rowsInline);
//                message.setReplyMarkup(markupInline);
//                try {
//                    execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else {
//            }
//        } else if (update.hasCallbackQuery()) {
//            // Set variables
//            String call_data = update.getCallbackQuery().getData();
//            long message_id = update.getCallbackQuery().getMessage().getMessageId();
//            long chat_id = update.getCallbackQuery().getMessage().getChatId();
//            if (call_data.equals("Reserva")) {
//                String answer = "Ingrese fecha de reserva";
//                EditMessageText new_message = new EditMessageText()
//                        .setChatId(chat_id)
//                        .setMessageId(toIntExact(message_id))
//                        .setText(answer);
//                try {
//                    execute(new_message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (call_data.equals("Modificar")) {
//                String answer = "Ingrese nueva fecha y hora";
//                TreatmentEntity treatmentEntity1 = treatmentDao.findById((long)3).get();
//                SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
//                        .setChatId(update.getMessage().getChatId())
//                        .setText("Persona desde BBDD: " + treatmentEntity1 + "\n" + " Materiales desde BBDD:" );
//                // .setText("Persona desde BBDD: " + personDto);
//                System.out.println(treatmentEntity1);
//
//
//                try {
//                    this.execute(message1);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            if (call_data.equals("Eliminar")) {
//                String answer = "Ingrese fecha y hora que desee eliminar";
//                EditMessageText new_message = new EditMessageText()
//                        .setChatId(chat_id)
//                        .setMessageId(toIntExact(message_id))
//                        .setText(answer);
//                try {
//                    execute(new_message);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//                    // }//fin menu de citas
//
//
//                }
//
//        }
//

//            List<String> chatResponse = new ArrayList<>();
//            CpUserEntity cpUser = initUser(update.getMessage().getFrom());
//            continueChatWithUser(update, cpUser, chatResponse);
//            return chatResponse;

            // TODO REVISAR  cpUser por que marca asi

// Si es la primera vez pedir una imagen para su perfil
//        if ()) {
//            LOGGER.info("Primer inicio de sesion para: {} ",update.getMessage().getFrom() );
//            result.add("Por favor ingrese una imagen para su foto de perfil");
//        } else { // Mostrar el menu de opciones
//            LOGGER.info("Dando bienvenida a: {} ",update.getMessage().getFrom() );
//            result.add("Bienvenido al Bot");
//        }

        }//fin clase BotBl


        /**
         * Procesa el chat con UN usuario
         * @param update El mensaje que envio el usuario
         * @param cpUserEntity El usuario con el que se esta interactuando
         * @param chatResponse Los mensajes que se desean retornar al usuario.
         */
//        private void continueChatWithUser(Update update, CpUserEntity cpUserEntity, List<String> chatResponse) {
//            // Obtener el ultimo mensaje que envió el usuario
//            IChatDao lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());
//            // Preparo la vaiable para retornar la respuesta
//            String response = null;
//            // Si el ultimo mensaje no existe (es la primera conversación)
//            if (lastMessage == null) {
//                // Retornamos 1
//                response = "1";
//            } else {
//                // Si existe convesasción previa iniciamos la variable del ultimo mensaje en 1
//                int lastMessageInt = 0;
//                try {
//                    // Intenemos obtener el ultimo mensaje retornado y lo convertimos a entero.
//                    // Si la coversin falla en el catch retornamos 1
//
//                    //TODO REPARAR EL ERROR
//                   // lastMessageInt = Integer.parseInt(lastMessage.getOutMessage());
//
//                    response = "" + (lastMessageInt + 1);
//                } catch (NumberFormatException nfe) {
//                    response = "1";
//                }
//            }
//            LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), cpUserEntity.getUserId());
//            // Creamos el objeto CpChat con la respuesta a la presente conversación.
//            CpChatEntity cpChat = new CpChatEntity();
//            cpChat.setCpUserUserId(cpUserEntity);
//            cpChat.setInMessage(update.getMessage().getText());
//            cpChat.setOutMessage(response);
//            cpChat.setMsgDate ( new Date()); //FIXME Obtener la fecha del campo entero update.getMessage().
//            cpChat.setTxDate (new Date()); //FIXME no se por q no da ese error se debe de recoger.
//            cpChat.setTxUser(cpUserEntity.getUserId().toString());
//            cpChat.setTxHost(update.getMessage().getChatId().toString());
//            // Guardamos en base dedatos
//            iChatDao.save (cpChat);
//            // Agregamos la respuesta al chatResponse.
//            chatResponse.add(response);
//        }

        /**
         * Si es la primera vez que el usuario conversa con el bot, se guarda su información en BBDD.
         * A futuro ademas de guardar la información captura el ultimo estado de la conversación.
         * @param user
         * @return first time login
         */
//        private boolean initUser(User user) {
//            System.out.println("Llego aca");
//            boolean result = true;
//            CpUserEntity cpUserEntity = iUserDao.findByBotUserId(user.getId().toString());
//            if (cpUserEntity == null) {
//                PersonEntity personEntity = new PersonEntity();
//                personEntity.setFirstName(user.getFirstName());
//                personEntity.setFirstSurname(user.getLastName());
//                personEntity.setStatus(Status.ACTIVE.getStatus());
//                personEntity.setTxHost("localhost");
//                personEntity.setTxUser("admin");
//                personEntity.setTxDate(new Date());
//                iPersonDao.save(personEntity);
//                cpUserEntity = new CpUserEntity();
//                cpUserEntity.setBotUserId(user.getId().toString());
//                cpUserEntity.setPersonId(personEntity);
//                cpUserEntity.setTxHost("localhost");
//                cpUserEntity.setTxUser("admin");
//                cpUserEntity.setTxDate(new Date());
//                iUserDao.save(cpUserEntity);
//                System.out.println("Llego aca");
//                result = true;
//            }
//
//                return result;
            //return cpUserEntity;






