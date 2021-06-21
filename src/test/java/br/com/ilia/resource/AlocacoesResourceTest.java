package br.com.ilia.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ilia.entity.dto.AlocacaoInsertDTO;

@SpringBootTest
public class AlocacoesResourceTest {
    
    @InjectMocks
    private AlocacoesResource resource = new AlocacoesResource();
    
    
    private AlocacaoInsertDTO payload;
    private ResponseEntity re;

    private String dia = "2021-06-18";
    private String tempo = "PT2H20M0S";
    private String nomeProjeto = "TestIlia";
    
    
    @Test
    public void alocarHorasNullTest() {
	re = resource.alocarHoras(null);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void alocarHorasDiaNullTest() {
	payload = new AlocacaoInsertDTO(null, tempo, nomeProjeto);
	re = resource.alocarHoras(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void alocarHorasTempoNullTest() {
	payload = new AlocacaoInsertDTO(dia, null, nomeProjeto);
	re = resource.alocarHoras(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void alocarHorasNomeProjetoNullTest() {
	payload = new AlocacaoInsertDTO(dia, tempo, null);
	re = resource.alocarHoras(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void alocarHorasDiaErroTest() {
	payload = new AlocacaoInsertDTO("2021bla", tempo, nomeProjeto);
	re = resource.alocarHoras(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void alocarHorasNewTest() {
	payload = new AlocacaoInsertDTO(dia, tempo, nomeProjeto);
	re = resource.alocarHoras(payload);
	assertEquals(re.getStatusCode(), HttpStatus.CREATED);
    }

}
