package com.bpanalytics.tasksmanager.framework;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return parse(parser.getText(), "dd/MM/yyyy", false);
    }
    
    public Date parse(String dateRep, String pattern, boolean lenient) {
		Date date = null;
		if (dateRep != null) {
			DateFormat df = null;
			if (pattern != null) {
				df = new SimpleDateFormat(pattern);
			} else {
				df = DateFormat.getInstance();
			}
			try {
				df.setLenient(lenient);
				date = new Date(df.parse(dateRep).getTime());
			} catch (Exception e) { 
				throw new RuntimeException(e);
			}
		}
		return date;
	}
}
