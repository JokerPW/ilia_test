package br.com.ilia.entity.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlocacaoReportDTOTest {
    
    @Mock
    private AlocacaoReportDTO dto;
    
    
    private String tempo = "PT2H00M0S";
    private String nomeProjeto = "TestIlia";
    
    
    @Before
    public void setUp() {
	dto = new AlocacaoReportDTO(tempo, nomeProjeto);
	System.out.println(dto.toString());
    }
    
    
    @Test
    public void getters() {
	assertEquals(tempo, dto.getTempo());
	assertEquals(nomeProjeto, dto.getNomeProjeto());
    }

    @Test
    public void adicionarTempoTest() {
	dto.adicionarTempo(tempo);
	assertEquals("PT4H00M0S", dto.getTempo());
    }
    
}
