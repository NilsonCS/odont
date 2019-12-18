package com.odont.odont.bl;

import com.odont.odont.bot.responseConversation;
import com.odont.odont.models.dao.*;
import com.odont.odont.models.dto.Status;
import com.odont.odont.models.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.math.BigDecimal;
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
    private IPatientDao iPatientDao;

    @Autowired
    public BotBl(IUserDao iUserDao, IPersonDao iPersonDao, IChatDao iChatDao, ITreatmentDao treatmentDao, IPatientDao iPatientDao) {

        this.iUserDao = iUserDao;
        this.iPersonDao = iPersonDao;
        this.iChatDao = iChatDao;
        this.treatmentDao = treatmentDao;
        this.iPatientDao = iPatientDao;
    }

    public List<responseConversation> processUpdate(Update update) {
        LOGGER.info("Ingresando a funcion processUpdate");
        // int response = 1;


        //   List<String> result = new ArrayList<>();
        List<responseConversation> chatResponse = new ArrayList<>();
        CpUserEntity cpUserEntity = initUser(update.getMessage().getFrom());

        continueChatWithUser(update, cpUserEntity, chatResponse);
        LOGGER.info("Ingresando a funcion processUpdate x999999992");
        try {
            LOGGER.info("Ingresando a funcion processUpdate x2");

            int a = 1;
            LOGGER.info("Recibiendo update {} ", update);

            // Si es la primera vez pedir una imagen para su perfil
            if (a == 1) {
                //if (initUser(update.getMessage().getFrom())) {
                //  result.add("Bienvenido al bot primer inicio de sesion");
            } else {
                // result.add("Bienvenido segunda xxx vez");
            }
        } catch (Exception ex) {
            LOGGER.warn("ERROR en processUpdate", ex);
            throw ex;
        }
        System.out.println("Chat response "+ chatResponse);
        return chatResponse;


    }






    private void continueChatWithUser(Update update, CpUserEntity cpUserEntity, List<responseConversation> chatResponse) {
        // Obtener el ultimo mensaje que envió el usuario
        // #NOTA# MI ERROR EN LA SIG LINEA FUE POR NO IChat dao gg
        //CpChatEntity lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());
        //CpChatEntity lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());
    CpChatEntity lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());


        System.out.println("OK! LLEGA ACA , Continue chat with user su valor es:"+lastMessage);
        // Preparo la vaiable para retornar la respuesta



         // Imprimiendo response
        List<responseConversation> responseConversations = new ArrayList<>();

        for(responseConversation responseConversation : responseConversations) {
            System.out.println(responseConversation.getConversation());
            System.out.println(responseConversation.getMessage());
        }

        for(int i = 0; i < responseConversations.size(); i++) {
            System.out.println(responseConversations.get(i).getConversation());
        }




        responseConversation response = null;
        // Si el ultimo mensaje no existe (es la primera conversación)
        if (lastMessage == null) {
            // Retornamos 1
            response = listResponses(0, 0, update.getMessage().getText(), update);

            //response = 1;


            // Imprimiendo response
            List<responseConversation> responseConversations2 = new ArrayList<>();

            for(responseConversation responseConversation : responseConversations2) {
                System.out.println(responseConversation.getConversation());
            }

            for(int i = 0; i < responseConversations2.size(); i++) {
                System.out.println(responseConversations.get(i).getConversation());
            }




        } else {
            System.out.println("OOOOOOOKKKKKK va por el lado del else con un valor de : "+ update.getMessage().getText());

            switch (update.getMessage().getText()) {
                case "Buscar Paciente":


                    // conversation y message van a la funcion responsestochatuser
                    // response = listResponses(10, lastMessage.getMessageId(), update.getMessage().getText(), update);
                    response = listResponses(10, lastMessage.getMessageId(), update.getMessage().getText(), update);

                    break;
                case "Registrar paciente":
                    System.out.println("Registrat paciente "+ update.getMessage().getText());
                    response = listResponses(20, lastMessage.getMessageId(), update.getMessage().getText(), update);
                    System.out.println("OOOOOOOKKKKKK va por el lado registrar paciente con un valor de : "+ update.getMessage().getText());

                    break;
                case "Ingresar Paciente":

                    LOGGER.warn("llega no llega x3");
                    response = listResponses(30, lastMessage.getMessageId(), update.getMessage().getText(), update);

                    break;

            }


            // Si existe convesasción previa iniciamos la variable del ultimo mensaje en 1
//            int lastMessageInt = 0;
//            try {
//                // Intenemos obtener el ultimo mensaje retornado y lo convertimos a entero.
//                // Si la coversin falla en el catch retornamos 1
//
//
//                lastMessageInt = Integer.parseInt(lastMessage.getOutMessage());
//
//
//                //response = "" + (lastMessageInt + 1);
//            } catch (NumberFormatException nfe) {
//                // response = "0";
//            }
        }

        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}", update.getMessage().getText(), cpUserEntity.getUserId());
        // Creamos el objeto CpChat con la respuesta a la presente conversación.
        CpChatEntity cpChatEntity = new CpChatEntity();
        cpChatEntity.setCpUserUserId(cpUserEntity);
        cpChatEntity.setInMessage(update.getMessage().getText());
        cpChatEntity.setOutMessage(response.getResponses());
       cpChatEntity.setInMessage(update.getMessage().getText());
       cpChatEntity.setConversationId(response.getConversation());
       cpChatEntity.setMessageId(response.getMessage());
       cpChatEntity.setMsgDate(new Date()); //FIXME Obtener la fecha del campo entero update.getMessage().
       cpChatEntity.setTxDate(new Date()); //FIXME no se por q no da ese error se debe de recoger.
       cpChatEntity.setTxUser(cpUserEntity.getUserId().toString());
       cpChatEntity.setTxHost(update.getMessage().getChatId().toString());

        // Guardamos en base dedatos
        iChatDao.save(cpChatEntity);
        // Agregamos la respuesta al chatResponse.
            chatResponse.add(response);
           // CpChatEntity lastMessage = update.getMessage().getText();
        System.out.println("OK! LLEGA ACA , Continue chat with user su valor es al finalizar:"+lastMessage);


    }


    private CpUserEntity initUser(User user) {
        System.out.println("Llego aca");
        //boolean result = true;
        CpUserEntity cpUserEntity = iUserDao.findByBotUserId(user.getId().toString());
       // CpUserEntity cpUserEntity = iUserDao.findByPersonId(user.getId().toString());
        if (cpUserEntity == null) {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setFirstName(user.getFirstName());
            personEntity.setFirstSurname(user.getLastName());
            personEntity.setStatus(Status.ACTIVE.getStatus());
            personEntity.setTxHost("localhost");
            personEntity.setTxUser("admin");
            personEntity.setTxDate(new Date());
            iPersonDao.save(personEntity);
            cpUserEntity = new CpUserEntity();
            cpUserEntity.setBotUserId(user.getId().toString());
            cpUserEntity.setPersonId(personEntity);
            cpUserEntity.setTxHost("localhost");
            cpUserEntity.setTxUser("admin");
            cpUserEntity.setTxDate(new Date());
            iUserDao.save(cpUserEntity);
            System.out.println("Llego a guardar en init user");
            //result = true;
        }
        // return result;
        return cpUserEntity;
    }


    private responseConversation listResponses(int conversation, int message, String messagereceived, Update update) {
        responseConversation responseConversation = new responseConversation();
        //CpUserEntity cpUserEntity = new CpUserEntity();
        switch (conversation) {
            case 0:
//inicio chat

                responseConversation.setResponses("Bienvenido a GatoscBot" +
                        "\nSus datos son los siguientes\n" +
                        update.getMessage().getFrom().getFirstName() + "  " + update.getMessage().getFrom().getLastName());
                responseConversation.setMessage(1);
                responseConversation.setConversation(10);

                System.out.println("Valor de message recived en case 0: "+messagereceived);


//                int lalo;
//                lalo = responseConversation.getMessage();
//                System.out.println(lalo + "Impresion del cambio de mensaje");

               // CpUserEntity cpUserEntity = iUserDao.findByBotUserId(update.getMessage().getChatId().toString());

                //responseConversation = switchRegisterPaciente(conversation, message, messagereceived, update, cpUserEntity);


                break;
            case 10:
                System.out.println("Hola");

               // responseConversation = switchMenuBuscar(message, messagereceived, update);


                break;
            case 20:

                //Se obtiene el person de la tabla user con el Chat_id que llega del update, para guardar


                LOGGER.info("PROCESSING IN MEsdasSSAGE: {} from user {}", update.getMessage().getText());

                CpUserEntity cpUserEntity = iUserDao.findByBotUserId(update.getMessage().getChatId().toString());
                responseConversation = switchRegisterPaciente(conversation, message, messagereceived, update, cpUserEntity);

                break;
//            case 30:
//                responsesReturn = switchMenuPaciente(conversation, message, messagereceived, update);
//
//                break;
//            case 40:
//                responsesReturn = switchMenuConfiguracion(message, messagereceived, update);
//
//                break;
        }
        System.out.println("Valor de message recived en case 0 despues del  breake: "+messagereceived);

        return responseConversation;


    }

    private responseConversation switchMenuBuscar(int message, String messagereceived, Update update) {
        responseConversation responseConversation = new responseConversation();

        switch (message) {
            case 1:
                responseConversation.setResponses("Ingresaaste Buscar paciente");
                responseConversation.setMessage(1);
                responseConversation.setConversation(0);
        }
        return responseConversation;
    }

    private responseConversation switchRegisterPaciente(int conversation, int message, String messagereceived, Update update, CpUserEntity cpUserEntity) {
        responseConversation responseConversation = new responseConversation();
        switch (message) {
            case 1:
                responseConversation.setResponses("Ingrese el nombre del paciente");
                responseConversation.setMessage(2);
                responseConversation.setConversation(conversation);

                break;
            case 2:
                responseConversation.setResponses("Ingrese la ciudad de nacimiento del paciente");
                responseConversation.setMessage(3);
                responseConversation.setConversation(conversation);
                break;
            case 3:
                responseConversation.setResponses("Ingrese la zona en la que vive el paciente");
                responseConversation.setMessage(4);
                responseConversation.setConversation(conversation);
                break;
            case 4:
                responseConversation.setResponses("Ingrese la calle del domicilio del paciente");
                responseConversation.setMessage(5);
                responseConversation.setConversation(conversation);
                break;

            case 5:
                responseConversation.setResponses("Ingrese la ubicacion del paciente");
                responseConversation.setMessage(6);
                responseConversation.setConversation(conversation);
                break;

            case 6:
                responseConversation.setResponses("Ingrese una imagen del paciente");
                responseConversation.setMessage(7);
                responseConversation.setConversation(conversation);
                break;

            case 7:

                responseConversation.setResponses("GRACIAS!!!!! \n Los datos se guardaron correctamente");
                responseConversation.setMessage(1);
                responseConversation.setConversation(30);
                PatientEntity patientEntity = null;
                patientEntity = returnPaciente(conversation, cpUserEntity, messagereceived);
                iPatientDao.save(patientEntity);
                break;
        }
        return responseConversation;
    }

    // Para guardar paciente
    private PatientEntity returnPaciente(int conversation, CpUserEntity cpuser, String lastmessage) {
        PatientEntity patientEntity = new PatientEntity();
        ArrayList<CpChatEntity> listRegisterPatient = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            CpChatEntity cpChatEntity = iChatDao.findMessageAndConversationByUserId(cpuser.getUserId(), conversation, i + 3);
            listRegisterPatient.add(cpChatEntity);
        }

        LOGGER.info(listRegisterPatient.get(0).getInMessage());
        LOGGER.info(listRegisterPatient.get(1).getInMessage());
        LOGGER.info(listRegisterPatient.get(2).getInMessage());
        LOGGER.info(listRegisterPatient.get(3).getInMessage());
        LOGGER.info(listRegisterPatient.get(4).getInMessage());

        //      patientEntity.setName(listRegisterPatient.get(0).getInMessage());
//        patientEntity.setCity(listRegisterRestaurant.get(1).getInMessage());
//        patientEntity.setCity(listRegisterRestaurant.get(1).getInMessage());
//        patientEntity.setZone(listRegisterRestaurant.get(2).getInMessage());
//        patientEntity.setZone(listRegisterRestaurant.get(2).getInMessage());
//        patientEntity.setStreet(listRegisterRestaurant.get(3).getInMessage());
//        patientEntity.setStreet(listRegisterRestaurant.get(3).getInMessage());
//        patientEntity.setLatitude(new BigDecimal(123.123));
//        patientEntity.setLatitude(new BigDecimal(123.123));
//        patientEntity.setLongitude(new BigDecimal(123.123));
//        patientEntity.setLongitude(new BigDecimal(123.123));
//        patientEntity.setImages(lastmessage);
//        patientEntity.setImages(lastmessage);
//        patientEntity.setStatus(1);
//        patientEntity.setStatus(1);
//        patientEntity.setTxUser("Admin");
//        patientEntity.setTxHost("localhost");
//        patientEntity.setTxDate(new Date());
//        patientEntity.setPersonId(cpuser.getPersonId());
        return patientEntity;

    }
    private responseConversation switchRegisterClientTreatment (int conversation, int message, String messagereceived, Update update, CpUserEntity cpuser) {
        responseConversation responsesReturn = new responseConversation();
        switch (message) {
            case 1:
                responsesReturn.setResponses("Ingrese el nombre del tratamiento");
                responsesReturn.setMessage(2);
                responsesReturn.setConversation(conversation);

                break;
            case 2:
                responsesReturn.setResponses("Ingrese el costo del tratamiento");
                responsesReturn.setMessage(3);
                responsesReturn.setConversation(conversation);
                break;
            case 3:
                responsesReturn.setResponses("Ingrese la duracion del tratamiento");
                responsesReturn.setMessage(4);
                responsesReturn.setConversation(conversation);
                break;
            case 7:

                responsesReturn.setResponses("GRACIAS!!!!! \n Los datos se guardaron correctamente");
                responsesReturn.setMessage(1);
                responsesReturn.setConversation(30);
                TreatmentEntity treatmentEntity = null;
                treatmentEntity = returnTreatment(conversation, cpuser, messagereceived);
                treatmentDao.save(treatmentEntity);
                break;
        }
        return responsesReturn;
    }
    private TreatmentEntity returnTreatment(int conversation, CpUserEntity cpuser, String lastmessage) {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        ArrayList<CpChatEntity> listTreatment = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            CpChatEntity cpChatEntity = iChatDao.findMessageAndConversationByUserId(cpuser.getUserId(), conversation, i + 3);
            listTreatment.add(cpChatEntity);
        }

        LOGGER.info(listTreatment.get(0).getInMessage());
        LOGGER.info(listTreatment.get(1).getInMessage());
        LOGGER.info(listTreatment.get(2).getInMessage());
        LOGGER.info(listTreatment.get(3).getInMessage());
        LOGGER.info(listTreatment.get(4).getInMessage());

        treatmentEntity.setNameTreatment(listTreatment.get(0).getInMessage());
        treatmentEntity.setDuration(listTreatment.get(1).getInMessage());
        treatmentEntity.setCostTreatment(Double.parseDouble(listTreatment.get(2).getInMessage()));
        return treatmentEntity;


    }



}
















//        public String guardarTratamientos (List<String> listaTratamientos){
//            TreatmentEntity treatmentEntity = new TreatmentEntity();
//            treatmentEntity.setNameTreatment(listaTratamientos.get(0));
//            treatmentEntity.setCostTreatment(Double.parseDouble(listaTratamientos.get(1)));
//            treatmentEntity.setDuration(listaTratamientos.get(2));
//            treatmentDao.save(treatmentEntity);
//            return "Se guardo correctamente";
//        }}

//        public List<String> processUpdate(Update update) {
//            LOGGER.info("Ingresando a funcion processUpdate");
//            List<String> result = new ArrayList<>();
//            List<String> chatResponse = new ArrayList<>();
//            CpUserEntity cpUserEntity =  initUser(update.getMessage().getFrom());
//            continueChatWithUser(update, cpUserEntity, chatResponse);



//            try {
//                LOGGER.info("Ingresando a funcion processUpdate x2");
//                int a = 1;
//                LOGGER.info("Recibiendo update {} ", update);
//
//                // Si es la primera vez pedir una imagen para su perfil
//                if(a==1){
//                    //if (initUser(update.getMessage().getFrom())) {
//                    result.add("Bienvenido al bot primer inicio de sesion");
//                } else {
//                    result.add("Bienvenido segunda xxx vez");
//                }
//            } catch (Exception ex){
//                LOGGER.warn("ERROR en processUpdate", ex);
//                throw ex;
//            }

//            return chatResponse;

//=======
                // Si es la primera vez pedir una imagen para su perfil
//                if (a == 1) {
//                    //if (initUser(update.getMessage().getFrom())) {
//                    result.add("Bienvenido al bot primer inicio de sesion");
//                } else {
//                    result.add("Bienvenido segunda xxx vez");
//                }
//            } catch (Exception ex) {
//                LOGGER.warn("ERROR en processUpdate", ex);
//                throw ex;
//            }

          //  return result;
        //}
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
//            } catch (Exception ex) {
//                LOGGER.warn("ERROR en processUpdate", ex);
//                throw ex;
//            }
//
//            return result;
//        }
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

//<<<<<<< HEAD







//fin clase BotBl








//        private boolean isNewUser(Update update) {
//            boolean response = false;
//            User user = update.getMessage().getFrom();
//            CpUserEntity cpUserEntity = iUserDao.findByBotUserId(user.getId().toString());
//            if (cpUserEntity == null) {
//                PersonEntity cpPerson = new PersonEntity();
//                cpPerson.setFirstName(user.getFirstName());
//
//                if (user.getLastName() == null) {
//                    cpPerson.setSecondName("-");
//                } else {
//                    cpPerson.setThirdName(user.getLastName());
//                }
//                cpPerson.setStatus(Status.ACTIVE.getStatus());
//                //cpPerson.setCarpool(0);//0 is for a not carpooler user
//                cpPerson.setTxHost("localhost");
//                cpPerson.setTxUser("admin");
//                cpPerson.setTxDate(new Date());
//                iPersonDao.save(cpPerson);
//                cpUserEntity = new CpUserEntity();
//                cpUserEntity.setBotUserId(user.getId().toString());
//                // cpUserEntity.setChatUserId(update.getMessage().getChatId().toString());
//                cpUserEntity.setPersonId(cpPerson);
//                // cpUserEntity.setConversationId(1);
//                cpUserEntity.setTxHost("localhost");
//                cpUserEntity.setTxUser("admin");
//                cpUserEntity.setTxDate(new Date());
//                iUserDao.save(cpUserEntity);
//                response = true;
//            }
//            return response;
//        }}


            /**
             * Procesa el chat con UN usuario
             * @param update El mensaje que envio el usuario
             * @param cpUserEntity El usuario con el que se esta interactuando
             * @param chatResponse Los mensajes que se desean retornar al usuario.
             */
//        private void continueChatWithUser(Update update, CpUserEntity cpUserEntity, List<String> chatResponse) {
//            // Obtener el ultimo mensaje que envió el usuario
//            // #NOTA# MI ERROR EN LA SIG LINEA FUE POR NO IChat dao gg
//            //CpChatEntity lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());
//            CpChatEntity lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());
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
//
//                    lastMessageInt = Integer.parseInt(lastMessage.getOutMessage());
//
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
//        private CpUserEntity initUser(User user) {
//            System.out.println("Llego aca");
//            //boolean result = true;
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
//                //result = true;
//            }
//               // return result;
//            return cpUserEntity;
//        }}
//=======


            /**
             * Procesa el chat con UN usuario
             * @param update El mensaje que envio el usuario
             * @param cpUserEntity El usuario con el que se esta interactuando
             * @param chatResponse Los mensajes que se desean retornar al usuario.
             */
//        private void continueChatWithUser(Update update, CpUserEntity cpUserEntity, List<String> chatResponse) {
//            // Obtener el ultimo mensaje que envió el usuario
//            // #NOTA# MI ERROR EN LA SIG LINEA FUE POR NO IChat dao gg
//            //CpChatEntity lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());
//            CpChatEntity lastMessage = iChatDao.findLastChatByUserId(cpUserEntity.getUserId());
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
//
//                    lastMessageInt = Integer.parseInt(lastMessage.getOutMessage());
//
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
//
//        /**
//         * Si es la primera vez que el usuario conversa con el bot, se guarda su información en BBDD.
//         * A futuro ademas de guardar la información captura el ultimo estado de la conversación.
//         * @param user
//         * @return first time login
//         */
//        private CpUserEntity initUser(User user) {
//            System.out.println("Llego aca");
//            //boolean result = true;
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
//                //result = true;
//            }

//               // return result;
//            return cpUserEntity;
//        }}

//
//                return result;
            //return cpUserEntity;


