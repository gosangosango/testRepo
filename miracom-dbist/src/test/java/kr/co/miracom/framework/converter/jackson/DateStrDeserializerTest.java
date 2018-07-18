package kr.co.miracom.framework.converter.jackson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

public class DateStrDeserializerTest {

    @Test
    public void test() throws Exception {
        String str = "{\"id\":\"2018-07-01\",\"testDate\":\"2018-07-02\"}";

        ObjectMapper mapper = new ObjectMapper();
        DateStrTest data = mapper.readValue(str, DateStrTest.class);
        System.out.println("testDate:" + data.getTestDate());
        assertEquals("Wrong Deserialize", data.getTestDate(), "20180702");
    }

    @Data
    public static class DateStrTest {
        private String id;
        @JsonDeserialize(using = DateStrDeserializer.class)
        private String testDate;
    }

}
