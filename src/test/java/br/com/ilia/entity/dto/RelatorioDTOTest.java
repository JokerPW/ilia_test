package br.com.ilia.entity.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RelatorioDTOTest {

    @Mock
    private RelatorioDTO dto;
    
    private String mes = "2021-06";
    private String horasTrabalhadas = "PT8H0S";
    private String horasExcedentes = "PT0S";
    private String horasDevidas = "PT0S";
    private RegistroDTO[] registros = new RegistroDTO[1];
    private AlocacaoReportDTO[] alocacoes = new AlocacaoReportDTO[1];

    
    private RegistroDTO registro;
    
    private String dia = "2021-06-18";
    private String[] horarios = {"09:00:00", "13:00:00", "14:00:00", "18:00:00"};
    
    
    private AlocacaoReportDTO alocacao;
    
    private String tempo = "PT2H00M0S";
    private String nomeProjeto = "TestIlia";
    
    
    @Before
    public void setUp() {
	registro = new RegistroDTO(dia, horarios);
	alocacao = new AlocacaoReportDTO(tempo, nomeProjeto);
	
	registros[0] = registro;
	alocacoes[0] = alocacao;
	
	dto = new RelatorioDTO(mes, horasTrabalhadas, horasExcedentes, horasDevidas, registros, alocacoes);
	System.out.println(dto.toString());
    }
    
    @SuppressWarnings("deprecation")
    @Test
    public void getters() {
	assertEquals(mes, dto.getMes());
	assertEquals(horasTrabalhadas, dto.getHorasTrabalhadas());
	assertEquals(horasExcedentes, dto.getHorasExcedentes());
	assertEquals(horasDevidas, dto.getHorasDevidas());
	assertEquals(registros, dto.getRegistros());
	assertEquals(alocacoes, dto.getAlocacoes());
    }
}
