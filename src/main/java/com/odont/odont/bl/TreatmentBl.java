package com.odont.odont.bl;

import com.odont.odont.models.dao.IChatDao;
import com.odont.odont.models.dao.ITreatmentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TreatmentBl {
    private static final Logger LOGGER= LoggerFactory.getLogger(TreatmentBl.class);
    private static int numero_pregunta = 0;
    private static int numero_respuesta = 0;
    private static int pregunta_respondida = 1;
    private static int respuesta_respondida = 1;
    private static boolean inicio_tratamiento = false;
    private static boolean confirmacion = false;
    private static List<String> tratamientos = new ArrayList<>();

    private ITreatmentDao treatmentDao;
    private IChatDao chatDao;

    public static int getNumero_pregunta() {
        return numero_pregunta;
    }

    public static void setNumero_pregunta(int numero_pregunta) {
        TreatmentBl.numero_pregunta = numero_pregunta;
    }

    public static int getNumero_respuesta() {
        return numero_respuesta;
    }

    public static void setNumero_respuesta(int numero_respuesta) {
        TreatmentBl.numero_respuesta = numero_respuesta;
    }

    public static int getPregunta_respondida() {
        return pregunta_respondida;
    }

    public static void setPregunta_respondida(int pregunta_respondida) {
        TreatmentBl.pregunta_respondida = pregunta_respondida;
    }

    public static int getRespuesta_respondida() {
        return respuesta_respondida;
    }

    public static void setRespuesta_respondida(int respuesta_respondida) {
        TreatmentBl.respuesta_respondida = respuesta_respondida;
    }

    public static boolean isInicio_tratamiento() {
        return inicio_tratamiento;
    }

    public static void setInicio_tratamiento(boolean inicio_tratamiento) {
        TreatmentBl.inicio_tratamiento = inicio_tratamiento;
    }

    public static boolean isConfirmacion() {
        return confirmacion;
    }

    public static void setConfirmacion(boolean confirmacion) {
        TreatmentBl.confirmacion = confirmacion;
    }

    public static List<String> getTratamientos() {
        return tratamientos;
    }

    public static void setTratamientos(List<String> tratamientos) {
        TreatmentBl.tratamientos = tratamientos;
    }

    public ITreatmentDao getTreatmentDao() {
        return treatmentDao;
    }

    public void setTreatmentDao(ITreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }

    public IChatDao getChatDao() {
        return chatDao;
    }

    public void setChatDao(IChatDao chatDao) {
        this.chatDao = chatDao;
    }
}
