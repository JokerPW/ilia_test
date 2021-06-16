package br.com.ilia.utils;

import org.springframework.http.HttpStatus;

public class GenericResponse {
    
    public static final String OK = "Created";
    public static final String DIA_CONCLUIDO = "Apenas 4 horários podem ser registrados por dia";
    public static final String FDS_PROIBIDO = "Sábado e domingo não são permitidos como dia de trabalho";
    public static final String HORARIO_PREVIO = "Horário já registrado";
    public static final String ALMOCO_INSUFICIENTE = "Deve haver no mínimo 1 hora de almoço";
    
    
    private String message;
    public String getMessage() { return message; }

    private HttpStatus status;
    public HttpStatus getStatus() { return status; }
    
    
    public GenericResponse (String message, HttpStatus status) {
	this.message = message;
	this.status = status;
    }

}
