package br.com.ilia.repository;

import java.util.Dictionary;
import java.util.Hashtable;

import br.com.ilia.entity.DiaTrabalhoEntity;

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
    
    
    private Dictionary<String, DiaTrabalhoEntity> dictDates;
    
    
    public FakeDatabase() {
	if (!canInstantiate)
	    throw new ExceptionInInitializerError("This object is a Singleton. Please, use its \"getInstance()\" method");
	
	dictDates = new Hashtable<String, DiaTrabalhoEntity>();
    }
    
    
    public DiaTrabalhoEntity getDay(String key) {
	
	if (dictDates.get(key) != null)
	    return dictDates.get(key);
	
	DiaTrabalhoEntity newDay = new DiaTrabalhoEntity();
	dictDates.put(key, newDay);
	return newDay;
    }
    
}
