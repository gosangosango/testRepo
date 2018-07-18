package kr.co.miracom.framework.util;

import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ValueUtilsTest {

    @Test
    public void test() throws Exception {
        // Object a = 0;
        // Assert.assertTrue("", a instanceof Integer);

        // Pattern pattern = Pattern.compile("^[_A-Za-z0-9.]*$");
        // Assert.assertTrue(pattern.matcher("test.gg").matches());
        // Assert.assertFalse(pattern.matcher("test#.gg").matches());
        // Assert.assertFalse(pattern.matcher("tes-t.gg").matches());
        // Assert.assertTrue(pattern.matcher("tes_t.gg").matches());

        // Pattern pattern = Pattern.compile("^[-A-Za-z0-9.]*$");
        // Assert.assertTrue(pattern.matcher("test01.gg").matches());
        // Assert.assertFalse(pattern.matcher("test01#.gg").matches());
        // Assert.assertFalse(pattern.matcher("tes_t01.gg").matches());
        // Assert.assertTrue(pattern.matcher("tes-t01.gg").matches());

        // String str = StringUtils.replace("myjung.jung", ".", "%2E");
        // System.out.println(str);
    }

    @Test
    public void testToDateStr() throws Exception {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";

        Date date = ValueUtils.toDate("2018-07-03T17:16:55", pattern);
        ZonedDateTime zonedDateTime = ValueUtils.toZonedDateTime(date);

        {
            String str = ValueUtils.toDateStr(date, pattern);
            Assert.assertEquals("Wrong Conversion!", "2018-07-03T17:16:55", str);
        }

        {
            String str = ValueUtils.toDateStr(zonedDateTime, pattern);
            Assert.assertEquals("Wrong Conversion!", "2018-07-03T17:16:55", str);
        }
    }

}
