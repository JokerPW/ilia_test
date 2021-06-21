package br.com.ilia.utils;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataConverterTest {
    
    @Test
    public void ptTimeFromPlainTest() {
	assertEquals("PT2H0S", DataConverter.ptTimeFromPlain("02:00:00"));
    }

    @Test
    public void plainFromPtTimeTest() {
	assertEquals("02:00:00", DataConverter.plainFromPtTime("PT2H0M0S"));
    }

    @Test
    public void ptTimeFromMilliTest() {
	LocalDateTime lDate = LocalDateTime.of(2021, Month.JUNE, 19, 9, 00);
	Instant instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date momento1 = Date.from(instant);
	
	lDate = LocalDateTime.of(2021, Month.JUNE, 19, 13, 00);
	instant = lDate.atZone(ZoneId.systemDefault()).toInstant();
	Date momento2 = Date.from(instant);
	
	assertEquals("PT4H0S", DataConverter.ptTimeFromMilli((momento2.getTime() - momento1.getTime())));
    }
    
}
