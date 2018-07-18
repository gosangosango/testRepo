package kr.co.miracom.framework.converter.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings({ "serial" })
public class DateStrSerializer extends StdSerializer<String> {

    protected DateStrSerializer() {
        this(null);
    }

    protected DateStrSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null && !value.contains("-")) {
            int len = value.length();
            if (len > 6) {
                value = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6);
            } else if (len > 4) {
                value = value.substring(0, 4) + "-" + value.substring(4);
            }
        }
        gen.writeString(value);
    }

}
