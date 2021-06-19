package br.com.ilia.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ilia.entity.DiaTrabalhoEntity;
import br.com.ilia.repository.FakeDatabase;
import br.com.ilia.utils.GenericResponse;

@Service
public class BatidasService {
    
    private FakeDatabase fdb = FakeDatabase.getInstance();
    

    public GenericResponse efetuarBatida (Date momento, String key) {
	
	GenericResponse ret = null;
	List<Date> marcacoes = fdb.getDay(key, true).getMarcacoes();
	Date last = null; 
	
	if (marcacoes.size() > 0)
	    last = marcacoes.get(marcacoes.size() - 1);

	if (marcacoes.size() >= 4) {
	    ret = new GenericResponse(GenericResponse.DIA_CONCLUIDO, HttpStatus.FORBIDDEN);
	    
	} else if (checarFDS(momento)) {
	    ret = new GenericResponse(GenericResponse.FDS_PROIBIDO, HttpStatus.FORBIDDEN);
		
	} else if (marcacoes.size() < 1) {
	    marcacoes.add(momento);
	    ret = new GenericResponse(GenericResponse.OK, HttpStatus.CREATED);
	    
	} else if (last.compareTo(momento) >= 0) {
	    ret = new GenericResponse(GenericResponse.HORARIO_PREVIO, HttpStatus.CONFLICT);
	    
	} else if (marcacoes.size() == 2) {	// Volta do almoço
	    if (calcularAlmocoMinimo(momento, last)) {
		marcacoes.add(momento);
		ret = new GenericResponse(GenericResponse.OK, HttpStatus.CREATED);
		
	    } else {
		ret = new GenericResponse(GenericResponse.ALMOCO_INSUFICIENTE, HttpStatus.FORBIDDEN);
	    }
	    
	} else {
	    marcacoes.add(momento);
	    ret = new GenericResponse(GenericResponse.OK, HttpStatus.CREATED);
	}
		
	return ret;
	
    }//--- End: efetuarBatida
    
    
    private boolean checarFDS(Date momento) {
	Calendar c = Calendar.getInstance();
	c.setTime(momento);
		
	return c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    }
    
    private boolean calcularAlmocoMinimo(Date momento, Date last) {
	return (momento.getTime() - last.getTime()) >= (60000);
    }
    
}
