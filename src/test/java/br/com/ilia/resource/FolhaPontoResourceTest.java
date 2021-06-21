package br.com.ilia.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class FolhaPontoResourceTest {

    @InjectMocks
    private FolhaPontoResource resource = new FolhaPontoResource();
    
    private ResponseEntity re;
    
    
    @Test
    public void extrairRelatorioMesNull() {
	resource.extrairRelatorio(null);
    }

    @Test
    public void extrairRelatorioMesErro() {
	re = resource.extrairRelatorio("202106");
	assertEquals(re.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void extrairRelatorioMesInexistente() {
	re = resource.extrairRelatorio("2023-06");
	assertEquals(re.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void extrairRelatorioOk() {
	re = resource.extrairRelatorio("2021-06");
	assertEquals(re.getStatusCode(), HttpStatus.OK);
    }
    
}
