package kr.co.miracom.framework.converter.jackson;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

public class DateStrSerializerTest {

    @Test
    public void test() throws Exception {
        DateStrTest data = new DateStrTest();
        data.setId("20180701");
        data.setTestDate("20180702");
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(data);
        System.out.println(str);
        assertThat(str, containsString("2018-07-02"));
    }

    @Data
    public static class DateStrTest {
        private String id;
        @JsonSerialize(using = DateStrSerializer.class)
        private String testDate;
    }

}
