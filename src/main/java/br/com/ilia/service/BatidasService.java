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
    

    @SuppressWarnings({ "deprecation" })
    public GenericResponse checarData (Date momento, String key) {
	
	GenericResponse ret = null;
	DiaTrabalhoEntity currDay = fdb.getDay(key);
	List<Date> marcacoes = currDay.getMarcacoes();
	Date last = null; 
	
	if (marcacoes.size() > 0)
	    last = marcacoes.get(marcacoes.size() - 1);

	Calendar c = Calendar.getInstance();
	c.setTime(momento);
		
	if (marcacoes.size() >= 4) {
	    ret = new GenericResponse(GenericResponse.DIA_CONCLUIDO, HttpStatus.FORBIDDEN);
	    
	} else if (c.get(Calendar.DAY_OF_WEEK) == 6 || c.get(Calendar.DAY_OF_WEEK) == 7) {
	    ret = new GenericResponse(GenericResponse.FDS_PROIBIDO, HttpStatus.FORBIDDEN);
		
	} else if (marcacoes.size() < 1) {
	    marcacoes.add(momento);
	    ret = new GenericResponse(GenericResponse.OK, HttpStatus.CREATED);
	    
	} else if (last != null && last.compareTo(momento) >= 0) {
	    ret = new GenericResponse(GenericResponse.HORARIO_PREVIO, HttpStatus.CONFLICT);
	    
	} else if (last != null && marcacoes.size() == 2) {	// Volta do almoço
	    if ((momento.getHours() * 60 + momento.getMinutes()) - 
		(last.getHours() * 60 + last.getMinutes()) >= 60) {
		
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
	
    }//--- End: checarData
    
}
