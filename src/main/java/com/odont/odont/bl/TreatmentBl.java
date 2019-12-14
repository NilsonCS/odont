package com.odont.odont.bl;

import com.odont.odont.ResponseReturn;
import com.odont.odont.models.dao.IChatDao;
import com.odont.odont.models.dao.IPersonDao;
import com.odont.odont.models.dao.ITreatmentDao;
import com.odont.odont.models.dao.IUserDao;
import com.odont.odont.models.entity.TreatmentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class TreatmentBl {
    private static final Logger LOGGER= LoggerFactory.getLogger(TreatmentBl.class);
    private ITreatmentDao treatmentDao;
    private IPersonDao personDao;
    private IUserDao userDao;

    @Autowired
    public TreatmentBl(ITreatmentDao treatmentDao, IPersonDao personDao, IUserDao userDao) {
        this.treatmentDao = treatmentDao;
        this.personDao = personDao;
        this.userDao = userDao;
    }

    public TreatmentBl(){
    }
    public ResponseReturn treamentRegister (int numberConversation, int numberMessage, String lastMessasge, Update update){
        String treatmentName="",cost= "",duration = "";
        ResponseReturn responsesReturn=new ResponseReturn();
        switch (numberMessage){
            case 1:
                responsesReturn.setResponses("Ingrese el nombre del tratamiento");
                responsesReturn.setMessage(2);
                responsesReturn.setConversation(1);
                break;
            case 2:
                treatmentName=treatmentName+update.getMessage().getText();
                responsesReturn.setResponses("Ingrese el nombre del tratamiento");
                responsesReturn.setMessage(3);
                responsesReturn.setConversation(1);
                LOGGER.info(treatmentName);
                break;
            case 3:
                cost=cost+update.getMessage().getText();
                responsesReturn.setResponses("Ingrese el costo del tratamiento");
                responsesReturn.setMessage(4);
                responsesReturn.setConversation(1);
                LOGGER.info(cost);
                break;
            case 4:
                duration=duration+(update.getMessage().getText());
                responsesReturn.setResponses("Ingrese la duracion del tratamiento");
                responsesReturn.setMessage(5);
                responsesReturn.setConversation(1);
                LOGGER.info(duration);

                break;

        }
        return responsesReturn;
    }
}
