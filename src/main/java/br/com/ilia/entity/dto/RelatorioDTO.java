package br.com.ilia.entity.dto;

public class RelatorioDTO {
    
    private String mes;
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    
    private String horasTrabalhadas;
    public String getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(String horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }

    private String horasExcedentes;
    public String getHorasExcedentes() { return horasExcedentes; }
    public void setHorasExcedentes(String horasExcedentes) { this.horasExcedentes = horasExcedentes; }

    private String horasDevidas;
    public String getHorasDevidas() { return horasDevidas; }
    public void setHorasDevidas(String horasDevidas) { this.horasDevidas = horasDevidas; }

    private Object[] registros;
    public Object[] getRegistros() { return registros; }
    public void setRegistros(Object[] registros) { this.registros = registros; }

    private Object[] alocacoes;
    public Object[] getAlocacoes() { return alocacoes; }
    public void setAlocacoes(Object[] alocacoes) { this.alocacoes = alocacoes; }

    
    public RelatorioDTO(String mes, String horasTrabalhadas, String horasExcedentes, String horasDevidas, 
	    Object[] registros, Object[] alocacoes) {

	this.mes = mes;
	this.horasTrabalhadas = horasTrabalhadas;
	this.horasExcedentes = horasExcedentes;
	this.horasDevidas = horasDevidas;
	this.registros = registros;
	this.alocacoes = alocacoes;
    }
    
    public RelatorioDTO(String mes, String horasTrabalhadas, String horasExcedentes, String horasDevidas,
	    RegistroDTO[] registros, AlocacaoReportDTO[] alocacoes) {
	this.mes = mes;
	this.horasTrabalhadas = horasTrabalhadas;
	this.horasExcedentes = horasExcedentes;
	this.horasDevidas = horasDevidas;
	this.registros = registros;
	this.alocacoes = alocacoes;
    }
    
    
    @Override
    public String toString() {
	StringBuilder ret = new StringBuilder("{");
	ret.append("\"mes\":\"" + mes + "\",");
	ret.append("\"horasTrabalhadas\":\"" + horasTrabalhadas + "\",");
	ret.append("\"horasExcedentes\":\"" + horasExcedentes + "\",");
	ret.append("\"horasDevidas\":\"" + horasDevidas + "\",");
	
	ret.append("\"registros\":[");
	for (int i = 0; i < registros.length; i++) {
	    if (i > 0)
		ret.append(",");
	    
	    ret.append(((RegistroDTO) registros[i]).toString());
	}
	ret.append("],");
	
	ret.append("\"alocacoes\":[");
	for (int i = 0; i < alocacoes.length; i++) {
	    if (i > 0)
		ret.append(",");
	    
	    ret.append(((AlocacaoReportDTO) alocacoes[i]).toString());
	}
	ret.append("]}");
	
	return ret.toString();
    }
}
