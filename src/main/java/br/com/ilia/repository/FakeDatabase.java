package br.com.ilia.repository;

import java.util.HashMap;

import br.com.ilia.entity.DiaTrabalhoEntity;
import br.com.ilia.entity.dto.AlocacaoInsertDTO;

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
    
    
    private HashMap<String, DiaTrabalhoEntity> hashMapDates;
    
    private HashMap<String, AlocacaoInsertDTO> hashMapAlocacoes;
    public HashMap<String, AlocacaoInsertDTO> getHashMapAlocacoes() { return hashMapAlocacoes; }
    
    
    public FakeDatabase() {
	if (!canInstantiate)
	    throw new ExceptionInInitializerError("This object is a Singleton. Please, use its \"getInstance()\" method");

	hashMapDates = new HashMap<String, DiaTrabalhoEntity>();
	hashMapAlocacoes = new HashMap<String, AlocacaoInsertDTO>();
    }
    
    
    public DiaTrabalhoEntity getDay(String key, boolean createNew) {
	
	if (hashMapDates.get(key) != null)
	    return hashMapDates.get(key);
	
	if (createNew) {
	    DiaTrabalhoEntity newDay = new DiaTrabalhoEntity();
	    hashMapDates.put(key, newDay);
	    return newDay;
	}
	
	return null;
    }

}
