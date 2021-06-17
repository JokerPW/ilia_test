package br.com.ilia.service;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.ilia.entity.DiaTrabalhoEntity;
import br.com.ilia.entity.dto.AlocacaoDTO;
import br.com.ilia.repository.FakeDatabase;
import br.com.ilia.utils.GenericResponse;

@Service
public class AlocacoesService {
    
    private final Long HOUR_MILI = (long) (60 * 60 * 1000);
    private final Long MIN_MILI = (long) (60 * 1000);
    private final Long SEC_MILI = (long) (1000);

    private FakeDatabase fdb = FakeDatabase.getInstance();

    
    public GenericResponse alocarHoras(AlocacaoDTO payload, Date momentoInformado) {

	GenericResponse ret = null;

	DiaTrabalhoEntity dia = fdb.getDay(payload.getDia());
	String alocKey = payload.getDia() + "_" + payload.getNomeProjeto();
	
	if (dia == null || dia.getMarcacoes() == null || dia.getMarcacoes().size() < 2) {
	    ret = new GenericResponse(GenericResponse.ALOCACAO_MAIOR, HttpStatus.BAD_REQUEST);
	    
	} else {
	    Long tempoMilli = calcularAlocacao(payload.getTempo());
	    Long slotMilli = calcularTempoDisponivel(dia);
	    Long ocupadoMilli = calcularAlocacaoDia(payload.getDia());
	    
	    if (tempoMilli <= (slotMilli - ocupadoMilli)) {
		ret = new GenericResponse(GenericResponse.ALOCADAS, HttpStatus.CREATED);
		fdb.getDictAlocacoes().put(alocKey, payload);
	    } else {
		ret = new GenericResponse(GenericResponse.ALOCACAO_MAIOR, HttpStatus.BAD_REQUEST);
	    }
	    
	}
	
	return ret;
    }
    
    
    // example: PT2H30M0S
    private Long calcularAlocacao(String yourTempo) {
	String tempo = yourTempo.substring(2).replaceAll("[a-zA-Z]","-");
	String[] tempoFields = tempo.split("-");
	
	Long tempoMilli = Long.parseLong(tempoFields[0]) * HOUR_MILI +
		Long.parseLong(tempoFields[1]) * MIN_MILI +
		Long.parseLong(tempoFields[2]) * SEC_MILI;
	
	return tempoMilli;
    }
    
    private Long calcularTempoDisponivel(DiaTrabalhoEntity dia) {
	if (dia == null || dia.getMarcacoes() == null || dia.getMarcacoes().size() < 2)
		return 0l;
	
	Long slotMilli = dia.getMarcacoes().get(1).getTime() - dia.getMarcacoes().get(0).getTime();
	if (dia.getMarcacoes().size() == 4)
	    slotMilli += dia.getMarcacoes().get(3).getTime() - dia.getMarcacoes().get(2).getTime();

	return slotMilli;
    }
    
    private Long calcularAlocacaoDia(String dayKey) {
	Long[] tempoAlocado = {0l};
	
	fdb.getDictAlocacoes().entrySet()
        .stream()
        .filter(entry -> ((AlocacaoDTO) entry.getValue()).getDia().split("T")[0].equals(dayKey))
        .forEach(entry -> tempoAlocado[0] += calcularAlocacao(((AlocacaoDTO) entry.getValue()).getTempo()));
	
	return tempoAlocado[0];
    }
    
}
