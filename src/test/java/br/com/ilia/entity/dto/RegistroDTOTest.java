package br.com.ilia.entity.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistroDTOTest {
    
    @Mock
    private RegistroDTO dto;
    
    
    private String dia = "2021-06-18";
    private String[] horarios = {"09:00:00", "13:00:00", "14:00:00", "18:00:00"};
    
    
    @Before
    public void setUp() {
	dto = new RegistroDTO(dia, horarios);
	System.out.println(dto.toString());
    }
    
    @SuppressWarnings("deprecation")
    @Test
    public void getters() {
	assertEquals(dia, dto.getDia());
	assertEquals(horarios, dto.getHorarios());
    }
}
