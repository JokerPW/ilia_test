package br.com.ilia.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class GenericResponseTest {
    
    @InjectMocks
    private GenericResponse gr;
    
    
    @Test
    public void constructorAndGetters() {
	gr = new GenericResponse(GenericResponse.OK, HttpStatus.CREATED);
	assertEquals(GenericResponse.OK, gr.getMessage());
	assertEquals(HttpStatus.CREATED, gr.getStatus());
    }
    
}
