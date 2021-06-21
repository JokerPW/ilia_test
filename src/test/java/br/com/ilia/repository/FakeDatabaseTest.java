package br.com.ilia.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ilia.entity.DiaTrabalhoEntity;

@SpringBootTest
public class FakeDatabaseTest {
    
    @InjectMocks
    private FakeDatabase fdb = FakeDatabase.getInstance();
    
    
    private DiaTrabalhoEntity entity = new DiaTrabalhoEntity();
    private String day = "2021-06-17";
    
    
    @Test
    public void getDayNull() {
	assertNull(fdb.getDay(day, false));
    }

    @Test
    public void getDayNew() {
	entity = fdb.getDay(day, true);
	assertNotNull(entity);
    }

    @Test
    public void getDayCompare() {
	assertEquals(entity, fdb.getDay(day, true));
    }
    
}
