package br.com.ilia.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ilia.entity.dto.RelatorioDTO;

@SpringBootTest
public class FolhaPontoServiceTest {
    
    @InjectMocks
    private FolhaPontoService service = new FolhaPontoService();
    
    
    private String key = "2021-06";
    private RelatorioDTO relatorio;
    
    
    @Test
    public void montarRelatorio() {
	relatorio = service.montarRelatorio(key);
	System.out.println(relatorio.toString());
    }

}
