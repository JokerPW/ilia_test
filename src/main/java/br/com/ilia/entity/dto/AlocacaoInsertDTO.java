package br.com.ilia.entity.dto;

public class AlocacaoInsertDTO {
    
    private String dia;
    public String getDia() { return dia; }

    private String tempo;
    public String getTempo() { return tempo; }

    private String nomeProjeto;
    public String getNomeProjeto() { return nomeProjeto; }
    
    
    public AlocacaoInsertDTO(String dia, String tempo, String nomeProjeto) {
	this.dia = dia;
	this.tempo = tempo;
	this.nomeProjeto = nomeProjeto;
    }
    
}
