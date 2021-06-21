package br.com.ilia.entity.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlocacaoInsertDTOTest {
    
    @Mock
    private AlocacaoInsertDTO dto;
    
    
    private String dia = "2021-06-18";
    private String tempo = "PT2H20M0S";
    private String nomeProjeto = "TestIlia";
    
    @Before
    public void setUp() {
	dto = new AlocacaoInsertDTO(dia, tempo, nomeProjeto);
    }
    
    @Test
    public void getters() {
	assertEquals(dia, dto.getDia());
	assertEquals(tempo, dto.getTempo());
	assertEquals(nomeProjeto, dto.getNomeProjeto());
    }
    
}
