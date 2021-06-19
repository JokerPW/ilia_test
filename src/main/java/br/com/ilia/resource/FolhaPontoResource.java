package br.com.ilia.resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ilia.entity.dto.RelatorioDTO;
import br.com.ilia.service.FolhaPontoService;
import br.com.ilia.utils.GenericResponse;

@RestController
@RequestMapping("/")
public class FolhaPontoResource {

    @Autowired
    FolhaPontoService service;
    

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping(value="folhas-de-ponto/{mes}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity extrairRelatorio(@PathVariable("mes") String mes) {
	
	if (mes == null || mes.length() < 7)
	    return new ResponseEntity ("Campo(s) obrigatório(s) não informado(s)", HttpStatus.BAD_REQUEST);
	
	RelatorioDTO relatorio = service.montarRelatorio(mes);
	
	if (relatorio != null) {
	    JSONObject response = new JSONObject();
	    response.put("relatorio", relatorio.toString());
	    return ResponseEntity.ok(response.toString());
	}
	
	return new ResponseEntity (GenericResponse.RELATORIO_NF, HttpStatus.NOT_FOUND);
	
    }//--- End: extrairRelatorio
    
}
