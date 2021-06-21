package br.com.ilia.entity;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiaTrabalhoEntityTest {
    
    @Mock
    private DiaTrabalhoEntity entity;
    
    
    @Before
    public void setUp() {
	// Constructor and getters
	entity = new DiaTrabalhoEntity();
	entity.getMarcacoes();
    }
    
    @Test
    public void adicionarMarcacaoTest1() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.OK, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoSameDateTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.HORARIO_PREVIO, entity.adicionarMarcacao(date));
    }
    
    @Test
    public void adicionarMarcacaoPreviousDateTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 8, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.HORARIO_PREVIO, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoSaturdayTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 19, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.FDS_PROIBIDO, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoSundayTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 20, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.FDS_PROIBIDO, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoLunchOutTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 13, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.OK, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoShortLunchTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 13, 50);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.ALMOCO_INSUFICIENTE, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoLunchFinishedTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 14, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.OK, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoEndOfDayTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 18, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.OK, entity.adicionarMarcacao(date));
    }

    @Test
    public void adicionarMarcacaoFifthTryTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 17, 18, 30);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	assertEquals(DiaTrabalhoEntity.dateStatus.DIA_CONCLUIDO, entity.adicionarMarcacao(date));
    }

}
