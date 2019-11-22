package com.odont.odont.bl;

import com.odont.odont.models.dao.IChatDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.IUserDao;
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
import bo.edu.ucb.sis.carpool.chatbot.dao.CpChatRepository;
import bo.edu.ucb.sis.carpool.chatbot.dao.CpPersonRepository;
import bo.edu.ucb.sis.carpool.chatbot.dao.CpUserRepository;
import bo.edu.ucb.sis.carpool.chatbot.domain.CpChat;
import bo.edu.ucb.sis.carpool.chatbot.domain.CpPerson;
import bo.edu.ucb.sis.carpool.chatbot.domain.CpUser;
import bo.edu.ucb.sis.carpool.chatbot.dto.Status;





    @Service
    public class BotBl {

        private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

        private IUserDao cpUserDao;
        private IPersonDao cpPersonDao;
        private IChatDao cpChatDao;

        @Autowired
        public BotBl(IUserDao cpUserDao, IPersonDao cpPersonDao, IChatDao cpChatDao) {
            this.cpUserDao = cpUserDao;
            this.cpPersonDao = cpPersonDao;
            this.cpChatDao = cpChatDao;
        }

        public List<String> processUpdate(Update update) {
            LOGGER.info("Recibiendo update {} ", update);
            List<String> chatResponse = new ArrayList<>();
            CpUser CpUser = initUser(update.getMessage().getFrom());
            continueChatWithUser(update, CpUser, chatResponse);
            return chatResponse;

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
         * @param cpUser El usuario con el que se esta interactuando
         * @param chatResponse Los mensajes que se desean retornar al usuario.
         */
        private void continueChatWithUser(Update update, CpUser cpUser, List<String> chatResponse) {
            // Obtener el ultimo mensaje que envió el usuario
            CpChat lastMessage = cpChatRepository.findLastChatByUserId(cpUser.getUserId());
            // Preparo la vaiable para retornar la respuesta
            String response = null;
            // Si el ultimo mensaje no existe (es la primera conversación)
            if (lastMessage == null) {
                // Retornamos 1
                response = "1";
            } else {
                // Si existe convesasción previa iniciamos la variable del ultimo mensaje en 1
                int lastMessageInt = 0;
                try {
                    // Intenemos obtener el ultimo mensaje retornado y lo convertimos a entero.
                    // Si la coversin falla en el catch retornamos 1
                    lastMessageInt = Integer.parseInt(lastMessage.getOutMessage());
                    response = "" + (lastMessageInt + 1);
                } catch (NumberFormatException nfe) {
                    response = "1";
                }
            }
            LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), cpUser.getUserId());
            // Creamos el objeto CpChat con la respuesta a la presente conversación.
            CpChat cpChat = new CpChat();
            cpChat.setCpUserUserId(cpUser);
            cpChat.setInMessage(update.getMessage().getText());
            cpChat.setOutMessage(response);
            cpChat.setMsgDate(new Date()); //FIXME Obtener la fecha del campo entero update.getMessage().
            cpChat.setTxDate(new Date());
            cpChat.setTxUser(cpUser.getUserId().toString());
            cpChat.setTxHost(update.getMessage().getChatId().toString());
            // Guardamos en base dedatos
            cpChatRepository.save(cpChat);
            // Agregamos la respuesta al chatResponse.
            chatResponse.add(response);
        }

        /**
         * Si es la primera vez que el usuario conversa con el bot, se guarda su información en BBDD.
         * A futuro ademas de guardar la información captura el ultimo estado de la conversación.
         * @param user
         * @return first time login
         */
        private CpUserEntity initUser(User user) {
            CpUserEntity cpUserEntity = cpUserDao.findByBotUserId(user.getId().toString());
            if (cpUserEntity == null) {
                PersonEntity Person = new PersonEntity();
                Person.setFirstName(user.getFirstName());
                Person.setFirstSurname(user.getLastName());
                Person.setStatus(Status.ACTIVE.getStatus());
                Person.setTxHost("localhost");
                Person.setTxUser("admin");
                Person.setTxDate(new Date());
                IPersonDao.save(Person);
                cpUserEntity = new CpUserEntity();
                cpUserEntity.setBotUserId(user.getId().toString());
                cpUserEntity.setPersonId(cpPerson);
                cpUserEntity.setTxHost("localhost");
                cpUserEntity.setTxUser("admin");
                cpUserEntity.setTxDate(new Date());
                cpUserDao.save(cpUserEntity);
            }
            return cpUserEntity;
        }

    }

}
