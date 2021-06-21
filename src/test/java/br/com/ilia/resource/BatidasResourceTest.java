package br.com.ilia.resource;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class BatidasResourceTest {

    @InjectMocks
    private BatidasResource resource = new BatidasResource();
    
    
    private Map<String, String> payload;
    private ResponseEntity re;
    
    
    @Before
    public void setUp() {
	
    }
    
    
    @Test
    public void baterPontoPayloadNull() {
	re = resource.baterPonto(null);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void baterPontoPayloadVazio() {
	payload = new HashMap<String, String>();
	re = resource.baterPonto(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void baterPontoPayloadSemCampo() {
	payload = new HashMap<String, String>();
	payload.put("erroKey", null);
	re = resource.baterPonto(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void baterPontoMomentoNull() {
	payload = new HashMap<String, String>();
	payload.put("momento", null);
	re = resource.baterPonto(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void baterPontoMomentoBranco() {
	payload = new HashMap<String, String>();
	payload.put("momento", "");
	re = resource.baterPonto(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void baterPontoMomentoErro() {
	payload = new HashMap<String, String>();
	payload.put("momento", "ERRO");
	re = resource.baterPonto(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void baterPontoMomentoNew() {
	payload = new HashMap<String, String>();
	payload.put("momento", "2021-06-17T09:00:00");
	re = resource.baterPonto(payload);
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    
}
