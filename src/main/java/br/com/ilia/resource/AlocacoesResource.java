package br.com.ilia.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ilia.entity.dto.AlocacaoInsertDTO;
import br.com.ilia.service.AlocacoesService;
import br.com.ilia.utils.GenericResponse;

@RestController
@RequestMapping("/")
public class AlocacoesResource {

    @Autowired
    AlocacoesService service;
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping(value="alocacao", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity alocarHoras(@RequestBody AlocacaoInsertDTO payload) {

	if (payload == null || 
	    payload.getDia() == null || payload.getDia().length() <= 0 ||
	    payload.getTempo() == null || payload.getTempo().length() <= 0 ||
	    payload.getNomeProjeto() == null || payload.getNomeProjeto().length() <= 0)
	    return new ResponseEntity ("Campo(s) obrigatório(s) não informado(s)", HttpStatus.BAD_REQUEST);

	Date momentoInformado;
	
	try {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    momentoInformado = simpleDateFormat.parse(payload.getDia());
	    
	} catch (ParseException pe) {
	    return new ResponseEntity ("Data e hora em formato inválido", HttpStatus.BAD_REQUEST);
	}

	GenericResponse ret = service.alocarHoras(payload, momentoInformado);
	return new ResponseEntity (ret.getMessage(), ret.getStatus());
	
    }//--- End: alocarHoras
    
}
