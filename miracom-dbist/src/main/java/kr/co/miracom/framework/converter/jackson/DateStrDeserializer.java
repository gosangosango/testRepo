package kr.co.miracom.framework.converter.jackson;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class DateStrDeserializer extends StdDeserializer<String> {

    protected DateStrDeserializer() {
        this(null);
    }

    protected DateStrDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String date = p.getText();
        date = StringUtils.replace(date, "-", "");
        return date;
    }

}
