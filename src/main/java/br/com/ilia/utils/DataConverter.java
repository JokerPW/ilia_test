package br.com.ilia.utils;

public class DataConverter {
    
    public final static Long DIA_DE_TRABALHO = (long) (8 * 60 * 60 * 1000);

    public final static Long HORA_ALMOCO = (long) (60 * 60 * 1000);

    public final static Long HOUR_MILLI = (long) (60 * 60 * 1000);
    public final static Long MIN_MILLI = (long) (60 * 1000);
    public final static Long SEC_MILLI = (long) (1000);

    
    public static String ptTimeFromPlain (String data) {
	String[] fields = data.split(":");
	
	int seconds = Integer.parseInt(fields[2]) % 60;
	int plusMinutes = Math.floorDiv(Integer.parseInt(fields[2]), 60);
	int minutes = (Integer.parseInt(fields[1]) + plusMinutes) % 60;
	int hours = Math.floorDiv(Integer.parseInt(fields[1]), 60) + Integer.parseInt(fields[0]);
	
	StringBuilder ret = new StringBuilder("PT");
	
	if (hours > 0)
	    ret.append(hours + "H");
	if (minutes > 0)
	    ret.append(minutes + "M");
	
	ret.append(seconds + "S");
	
	return ret.toString();
    }
    
    public static String plainFromPtTime (String data) {
	return data.substring(2).replaceAll("[a-zA-Z]",":");
    }
    
    public static String ptTimeFromMilli (Long milli) {
	
	StringBuilder ret = new StringBuilder("PT");
	Long hours = Math.floorDiv (milli, HOUR_MILLI);
	Long minutes = Math.floorDiv ((milli - (hours * HOUR_MILLI)), MIN_MILLI);
	Long seconds = milli - ((hours * HOUR_MILLI) + (minutes * MIN_MILLI));

	if (hours > 0)
	    ret.append(hours + "H");
	if (minutes > 0)
	    ret.append(minutes + "M");
	
	ret.append(seconds + "S");
	
	return ret.toString();
    }

}
