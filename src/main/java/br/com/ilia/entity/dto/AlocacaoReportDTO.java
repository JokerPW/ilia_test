package br.com.ilia.entity.dto;

import br.com.ilia.utils.DataConverter;

public class AlocacaoReportDTO {

    private String tempo;
    public String getTempo() { return tempo; }

    private String nomeProjeto;
    public String getNomeProjeto() { return nomeProjeto; }
    
    
    public AlocacaoReportDTO(String tempo, String nomeProjeto) {
	this.tempo = tempo;
	this.nomeProjeto = nomeProjeto;
    }
    
    @Override
    public String toString() {
	return "{\"nomeProjeto\":\"" + getNomeProjeto() + "\",\"tempo\":\"" + getTempo() + "\"}";
    }

    public void adicionarTempo (String currTempo) {
	String[] countOld = DataConverter.plainFromPtTime(getTempo()).split(":");
	String[] countNew = DataConverter.plainFromPtTime(currTempo).split(":");
	
	this.tempo = DataConverter.ptTimeFromPlain(
		String.format("%02d", ((Integer.parseInt(countOld[0]) + (Integer.parseInt(countNew[0]))))) + ":" +
		String.format("%02d", ((Integer.parseInt(countOld[1]) + (Integer.parseInt(countNew[1]))))) + ":" +
		String.format("%02d", ((Integer.parseInt(countOld[2]) + (Integer.parseInt(countNew[2])))))
	);
    }
    
}
