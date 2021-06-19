package br.com.ilia.entity.dto;

public class RegistroDTO {
    
    private String dia;
    public String getDia() { return dia; }

    private String[] horarios;
    public String[] getHorarios() { return horarios; }
    

    public RegistroDTO(String dia, String[] horarios) {
	this.dia = dia;
	this.horarios = horarios;
    }
    
    
    @Override
    public String toString() {
	StringBuilder ret = new StringBuilder("{");
	ret.append("\"dia\":\"" + getDia() + "\",");
	ret.append("\"horarios\":[");
	for (int i = 0; i < getHorarios().length; i++) {
	    if (i > 0)
		ret.append(",");
		
	    ret.append("\"" + getHorarios()[i] + "\"");
	}
	ret.append("]}");
	return ret.toString();
    }
}
