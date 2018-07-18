package kr.co.miracom.dbist.util;

import java.time.ZoneId;

import org.junit.Assert;
import org.junit.Test;

public class DbistConfigTest {

    /**
     * Test method for {@link kr.co.miracom.dbist.util.DbistConfig#getZoneId()}.
     */
    @Test
    public void testGetDefaultZoneId() {
        Assert.assertEquals("Default ZoneId is different with System Default", ZoneId.systemDefault(),
                        DbistConfig.getZoneId());
    }

    /**
     * Test method for {@link kr.co.miracom.dbist.util.DbistConfig#setZoneId()}.
     */
    @Test
    public void testSetZoneId() throws Exception {
        try {
            DbistConfig.setZoneId(ZoneId.of("UTC"));
            Assert.assertEquals("ZoneId should be changed after Set as the Value", ZoneId.of("UTC"),
                            DbistConfig.getZoneId());

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Assert.assertEquals("Set ZoneId should not affect to Other Threads", ZoneId.systemDefault(),
                                    DbistConfig.getZoneId());
                }

            }, "testSetZoneId-innerThread");
            thread.start();

            thread.join();

        } finally {
            DbistConfig.setZoneId(null);
            Assert.assertEquals("null ZoneId should be same with System Default", ZoneId.systemDefault(),
                            DbistConfig.getZoneId());
        }
    }

}
