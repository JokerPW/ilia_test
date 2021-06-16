package br.com.ilia.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DiaTrabalhoEntity {
    
    /**
     * OK - Data incluída com sucesso
     * DIA_CONCLUIDO - Dia já teve todos os seus horários incluídos
     * ALMOCO_INSUFICIENTE - Horário de almoço não respeita mínimo de 1h
     * FDS_PROIBIDO - Tentativa de inclusão de fim de semana
     * HORARIO_PREVIO - Horário já havia sido incluído ou menor do que o último horário incluído.
     */
    public static enum dateStatus {
	OK, DIA_CONCLUIDO, ALMOCO_INSUFICIENTE, FDS_PROIBIDO, HORARIO_PREVIO
    }

    
    private List<Date> marcacoes;
    public List<Date> getMarcacoes() { return marcacoes; }
    
    
    
    public DiaTrabalhoEntity() {
	marcacoes = new ArrayList<Date>();
    }
    
    
    @SuppressWarnings("deprecation")
    public dateStatus adicionarMarcacao(Date yourDate) {
	
	if (marcacoes.size() >= 4)
	    return dateStatus.DIA_CONCLUIDO;
	
	Calendar c = Calendar.getInstance();
	c.setTime(yourDate);
	
	if (c.get(Calendar.DAY_OF_WEEK) == 6 || c.get(Calendar.DAY_OF_WEEK) == 7)
	    return dateStatus.FDS_PROIBIDO;
	
	if (marcacoes.size() < 1) {
	    marcacoes.add(yourDate);
	    return dateStatus.OK;
	}
	
	Date last = marcacoes.get(marcacoes.size() - 1);
	
	if (last.compareTo(yourDate) >= 0)
	    return dateStatus.HORARIO_PREVIO;
	
	if (marcacoes.size() == 2) {	// Volta do almoço
	    if ((yourDate.getHours() * 60 + yourDate.getMinutes()) - 
		(last.getHours() * 60 + last.getMinutes()) >= 60) {
		
		marcacoes.add(yourDate);
		return dateStatus.OK;
	    }
	    
	    return dateStatus.ALMOCO_INSUFICIENTE;
	}
	
	marcacoes.add(yourDate);
	return dateStatus.OK;
    }
    
}
