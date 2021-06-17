package br.com.ilia.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ilia.service.BatidasService;
import br.com.ilia.utils.GenericResponse;

@RestController
@RequestMapping("/components/schemas")
public class BatidasResource {
    
    @Autowired
    BatidasService service;
    
    
    // example: "2018-08-22T08:00:00"
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(value="/momento", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity baterPonto(@RequestBody Map<String, ?> payload) {
    
	String momento = (String) payload.get("momento");
	
	if (momento == null || momento.length() <= 0)
	    return new ResponseEntity ("Campo obrigatório não informado", HttpStatus.BAD_REQUEST);
	
	Date momentoInformado;
	
	try {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    momentoInformado  = simpleDateFormat.parse(momento);
	    
	} catch (ParseException pe) {
	    return new ResponseEntity ("Data e hora em formato inválido", HttpStatus.BAD_REQUEST);
	}

	String key = momento.split("T")[0];
	GenericResponse ret = service.efetuarBatida(momentoInformado, key);
	return new ResponseEntity (ret.getMessage(), ret.getStatus());
	
    }//--- End: baterPonto
    
    
}
