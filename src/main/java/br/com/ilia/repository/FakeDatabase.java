package br.com.ilia.repository;

import java.util.HashMap;

import br.com.ilia.entity.DiaTrabalhoEntity;
import br.com.ilia.entity.dto.AlocacaoDTO;

public class FakeDatabase {
    
    
    private static boolean canInstantiate = false;
    private static FakeDatabase instance;
    public static FakeDatabase getInstance() {
	if (instance == null) {
	    canInstantiate = true;
	    instance = new FakeDatabase();
	    canInstantiate = false;
	}
	
	return instance;
    }
    
    
    private HashMap<String, DiaTrabalhoEntity> dictDates;
    
    private HashMap<String, AlocacaoDTO> dictAlocacoes;
    public HashMap<String, AlocacaoDTO> getDictAlocacoes() { return dictAlocacoes; }
    
    
    public FakeDatabase() {
	if (!canInstantiate)
	    throw new ExceptionInInitializerError("This object is a Singleton. Please, use its \"getInstance()\" method");

	dictDates = new HashMap<String, DiaTrabalhoEntity>();
	dictAlocacoes = new HashMap<String, AlocacaoDTO>();
    }
    
    
    public DiaTrabalhoEntity getDay(String key) {
	
	if (dictDates.get(key) != null)
	    return dictDates.get(key);
	
	DiaTrabalhoEntity newDay = new DiaTrabalhoEntity();
	dictDates.put(key, newDay);
	return newDay;
    }

}
