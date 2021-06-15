package br.com.ilia.utils;

import lombok.Data;


public @Data class GenericResponse {
    
    public static final String OK = "Created";
    public static final String DIA_CONCLUIDO = "Apenas 4 horários podem ser registrados por dia";
    public static final String FDS_PROIBIDO = "Sábado e domingo não são permitidos como dia de trabalho";
    public static final String HORARIO_PREVIO = "Horário já registrado";
    public static final String ALMOCO_INSUFICIENTE = "Deve haver no mínimo 1 hora de almoço";
    
    private String message;
    private int status;
    
    
    public GenericResponse (String message, int status) {
	this.message = message;
	this.status = status;
    }
    
}
