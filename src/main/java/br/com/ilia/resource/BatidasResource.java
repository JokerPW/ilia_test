package br.com.ilia.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ilia.service.BatidasService;
import br.com.ilia.utils.GenericResponse;

@RestController
@RequestMapping("/components/schemas")
public class BatidasResource {
    
    
    @Autowired
    BatidasService service;
    
    // example: "2018-08-22T08:00:00"
    @SuppressWarnings("rawtypes")
    @PostMapping(value="/momento", consumes = "multipart/form-data")
    public ResponseEntity BaterPonto(@RequestParam("momento") String momento) {
    
	if (momento == null || momento.length() <= 0)
	    return new ResponseEntity ("Campo obrigatório não informado", HttpStatus.BAD_REQUEST);
	
	Date momentoInformado;
	
	try {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    momentoInformado  = simpleDateFormat.parse(momento);
	    
	} catch (ParseException pe) {
	    return new ResponseEntity ("Data e hora em formato inválido", HttpStatus.BAD_REQUEST);
	}
	
	GenericResponse ret = service.checarData(momentoInformado);
	if (ret.getMessage().length() > 0)
	    return new ResponseEntity (ret.getMessage(), HttpStatus.FORBIDDEN);
	
	
	
	return new ResponseEntity ("", HttpStatus.CREATED);
	
    }//--- End: BaterPonto
    
    
}
