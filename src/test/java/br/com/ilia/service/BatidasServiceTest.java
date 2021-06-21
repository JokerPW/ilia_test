package br.com.ilia.service;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import br.com.ilia.utils.GenericResponse;

@SpringBootTest
public class BatidasServiceTest {
    
    @InjectMocks
    private BatidasService service = new BatidasService();
    
    
    private Date momento;
    private String key = "2021-06-17";

    private GenericResponse ge;

    
    @Test
    public void efetuarBatidaFDS() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 19, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.FDS_PROIBIDO);
	assertEquals(ge.getStatus(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void efetuarBatidaEntrada() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.OK);
	assertEquals(ge.getStatus(), HttpStatus.CREATED);
    }

    @Test
    public void efetuarBatidaRepetida() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.HORARIO_PREVIO);
	assertEquals(ge.getStatus(), HttpStatus.CONFLICT);
    }

    @Test
    public void efetuarBatidaAlmocoSaida() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 13, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.OK);
	assertEquals(ge.getStatus(), HttpStatus.CREATED);
    }

    @Test
    public void efetuarBatidaAlmocoMenor() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 13, 30);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.ALMOCO_INSUFICIENTE);
	assertEquals(ge.getStatus(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void efetuarBatidaAlmocoVolta() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 14, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.OK);
	assertEquals(ge.getStatus(), HttpStatus.CREATED);
    }

    @Test
    public void efetuarBatidaEncerrandoDia() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 18, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.OK);
	assertEquals(ge.getStatus(), HttpStatus.CREATED);
    }

    @Test
    public void efetuarBatidaDiaEncerrado() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 18, 30);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	momento = Date.from(instant);
	
	ge = service.efetuarBatida(momento, key);
	assertEquals(ge.getMessage(), GenericResponse.DIA_CONCLUIDO);
	assertEquals(ge.getStatus(), HttpStatus.FORBIDDEN);
    }

}
