package br.com.ilia.service;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import br.com.ilia.entity.dto.AlocacaoInsertDTO;
import br.com.ilia.utils.GenericResponse;

@SpringBootTest
public class AlocacoesServiceTest {
    
    @InjectMocks
    private AlocacoesService service = new AlocacoesService();
    
    
    private AlocacaoInsertDTO payload;
    private Date momentoInformado;
    
    private GenericResponse ge;

    private String dia = "2021-06-18";
    private String tempo = "PT2H20M0S";
    private String nomeProjeto = "TestIlia";
    
    
    @Before
    public void setUp() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momentoInformado = Date.from(instant);
    }
    
    
    @Test
    public void alocarHorasSemMaracacoes() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 16, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date momentoanterior = Date.from(instant);
	
	payload = new AlocacaoInsertDTO(dia, tempo, nomeProjeto);
	ge = service.alocarHoras(payload, momentoanterior);
	assertEquals(ge.getMessage(), GenericResponse.ALOCACAO_MAIOR);
	assertEquals(ge.getStatus(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void alocarHorasMaiores() {
	String tempoAMais = "PT8H20M0S";
	payload = new AlocacaoInsertDTO(dia, tempoAMais, nomeProjeto);
	ge = service.alocarHoras(payload, momentoInformado);
	assertEquals(ge.getMessage(), GenericResponse.ALOCACAO_MAIOR);
	assertEquals(ge.getStatus(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void alocarHorasCertas() {
	payload = new AlocacaoInsertDTO(dia, tempo, nomeProjeto);
	ge = service.alocarHoras(payload, momentoInformado);
	assertEquals(ge.getMessage(), GenericResponse.ALOCADAS);
	assertEquals(ge.getStatus(), HttpStatus.CREATED);
    }
    
}
