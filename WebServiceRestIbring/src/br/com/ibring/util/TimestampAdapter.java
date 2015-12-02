package br.com.ibring.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

//public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
public class TimestampAdapter extends XmlAdapter<String, Timestamp> {
	
	public static final String FORMAT ="yyyy-MM-dd HH:mm:ss";
	
	public String marshal(Timestamp v) {
		return new SimpleDateFormat(FORMAT).format(v);
	}

	@Override
	public Timestamp unmarshal(String v) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);  
		return new Timestamp(sdf.parse(v).getTime()); 

	}
}