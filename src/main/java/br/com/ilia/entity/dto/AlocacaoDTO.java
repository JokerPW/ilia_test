package br.com.ilia.entity.dto;

public class AlocacaoDTO {
    
    private String dia;
    public String getDia() { return dia; }

    private String tempo;
    public String getTempo() { return tempo; }

    private String nomeProjeto;
    public String getNomeProjeto() { return nomeProjeto; }
    
    
    public AlocacaoDTO(String dia, String tempo, String nomeProjeto) {
	super();
	this.dia = dia;
	this.tempo = tempo;
	this.nomeProjeto = nomeProjeto;
    }

}
