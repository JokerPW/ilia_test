package br.com.ilia.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ilia.entity.DiaTrabalhoEntity;
import br.com.ilia.entity.dto.AlocacaoReportDTO;
import br.com.ilia.entity.dto.RegistroDTO;
import br.com.ilia.entity.dto.RelatorioDTO;
import br.com.ilia.repository.FakeDatabase;
import br.com.ilia.utils.DataConverter;

@Service
public class FolhaPontoService {
    
    private FakeDatabase fdb = FakeDatabase.getInstance();

    
    public RelatorioDTO montarRelatorio(String mes) {
	RelatorioDTO ret = null;
	
	Long horasTrabalhadas = 0l;
	int diasTrabalhados = 0;
	
	ArrayList<RegistroDTO> registros = new ArrayList<RegistroDTO>();
	HashMap<String, AlocacaoReportDTO> alocacoes = new HashMap<String, AlocacaoReportDTO>();
	
	for (int i = 1; i <= 31; i++) {
	    String fullDate = mes + "-" + String.format("%02d", i);
	    DiaTrabalhoEntity currDay = fdb.getDay(fullDate, false);
	    
	    if (currDay != null && currDay.getMarcacoes() != null && currDay.getMarcacoes().size() > 0) {
		diasTrabalhados++;
		horasTrabalhadas += calcularHorasTrabalhadas(currDay);
		registros.add(new RegistroDTO(fullDate, montarRegistros(currDay.getMarcacoes())));
	    }
	    
	    calcularAlocacoes(fullDate, alocacoes);
	}
	
	Long horasEstimadas = diasTrabalhados * DataConverter.DIA_DE_TRABALHO;
	Long horasExcedentes = (horasTrabalhadas - horasEstimadas > 0) ? horasTrabalhadas - horasEstimadas : 0l;
	Long horasDevidas = (horasEstimadas - horasTrabalhadas > 0) ? horasEstimadas - horasTrabalhadas : 0l;
	
	if (registros.size() > 0 && alocacoes.size() > 0) {
	    ret = new RelatorioDTO (
		    mes,
		    DataConverter.ptTimeFromMilli(horasTrabalhadas),
		    DataConverter.ptTimeFromMilli(horasExcedentes),
		    DataConverter.ptTimeFromMilli(horasDevidas),
		    registros.toArray(),
		    alocacoes.values().toArray()
		);
	}
	
	return ret;
	
    }//--- End: montarRelatorio

    
    private Long calcularHorasTrabalhadas (DiaTrabalhoEntity currDay) {
	Long horasTrabalhadas = 0l;
	if (currDay != null && currDay.getMarcacoes() != null && currDay.getMarcacoes().size() > 0) {
	    
	    int count = currDay.getMarcacoes().size();
	    if (count >= 2)
		horasTrabalhadas += currDay.getMarcacoes().get(1).getTime() - 
			currDay.getMarcacoes().get(0).getTime();

	    if (count == 4)
		horasTrabalhadas += currDay.getMarcacoes().get(3).getTime() - 
			currDay.getMarcacoes().get(2).getTime();
	    
	}
	
	return horasTrabalhadas;
    }
    
    
    private String[] montarRegistros (List<Date> marcacoes) {
	String[] horarios = new String[4];
	int ndx = 0;
	for (Date horario : marcacoes) {
	    horarios[ndx++] = new SimpleDateFormat("HH:mm:ss").format(horario);
	}
	
	return horarios;
    }

    
    private void calcularAlocacoes(String fullDate, HashMap<String, AlocacaoReportDTO> alocacoes) {
	for (String key : fdb.getHashMapAlocacoes().keySet()) {
	    
	    String[] fullKey = key.split("_");
	    if (fullDate.equals(fullKey[0])) {
		
		// Se ainda não registrou as alocacoes deste projeto, cria objeto novo
		if (alocacoes.get(fullKey[1]) == null) {
		    alocacoes.put(fullKey[1], new AlocacaoReportDTO(
			    fdb.getHashMapAlocacoes().get(key).getTempo(), fullKey[1]));
		    
		} else { // Se já houver alocacao, somar o tempo
		    alocacoes.get(fullKey[1]).adicionarTempo(
			    fdb.getHashMapAlocacoes().get(key).getTempo());
		}
		
	    }
	    
	}
	
    }//--- End: calcularAlocacoes

}
