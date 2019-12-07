package com.odont.odont.bl;

import com.odont.odont.models.dao.IChatDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.IUserDao;
import com.odont.odont.models.dto.Status;
import com.odont.odont.models.entity.CpChatEntity;
import com.odont.odont.models.entity.CpUserEntity;
import com.odont.odont.models.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    @Service
    public class BotBl {

        private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

        private IUserDao iUserDao;
        private IPersonDao iPersonDao;
        private IChatDao iChatDao;

        @Autowired
        public BotBl(IUserDao iUserDao, IPersonDao iPersonDao, IChatDao iChatDao) {
            this.iUserDao = iUserDao;
            this.iPersonDao = iPersonDao;
            this.iChatDao = iChatDao;
        }



        public List<String> processUpdate(Update update) {
            LOGGER.info("Ingresando a funcion processUpdate");
            List<String> result = new ArrayList<>();
            List<String> chatRespone = new ArrayList<>();
            CpUserEntity cpUserEntity =  initUser(update.getMessage().getFrom());
            try {
                LOGGER.info("Ingresando a funcion processUpdate x2");
                int a = 1;
                LOGGER.info("Recibiendo update {} ", update);

                // Si es la primera vez pedir una imagen para su perfil
                if(a==1){
                    //if (initUser(update.getMessage().getFrom())) {
                    result.add("Bienvenido al bot primer inicio de sesion");
                } else {
                    result.add("Bienvenido segunda xxx vez");
                }
            } catch (Exception ex){
                LOGGER.warn("ERROR en processUpdate", ex);
                throw ex;
            }

            return result;


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

        }


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
        private CpUserEntity initUser(User user) {
            System.out.println("Llego aca");
            //boolean result = true;
            CpUserEntity cpUserEntity = iUserDao.findByBotUserId(user.getId().toString());
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
                System.out.println("Llego aca");
                //result = true;
            }
               // return result;
            return cpUserEntity;
        }}




