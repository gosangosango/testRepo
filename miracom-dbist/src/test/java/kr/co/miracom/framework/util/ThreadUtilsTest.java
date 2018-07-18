/**
 * Copyright (c) 1998-2018 Miracom Inc. All rights reserved.
 *
 * Don't copy or redistribute this source code without permission.
 * This software is provided "As Is" and any expressed or implied
 * warranties, including, but not limited to, the implied warranties of
 * merchantability and fitness for a particular purpose are disclaimed.
 * In no event shall Miracom Inc. or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential
 * damages including, but not limited to, procurement of substitute
 * goods or services; loss of use, data, or profits; or business
 * interruption) however caused and on any theory of liability, whether
 * in contract, strict liability, or tort (including negligence or otherwise)
 * arising in any way out of the use of this software, even if advised
 * of the possibility of such damage.
 *
 * For more information on this product, please see
 * http://www.miracom.co.kr
 */
package kr.co.miracom.framework.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author myjung.jung
 */
public class ThreadUtilsTest {

    /**
     * Test method for {@link kr.co.miracom.framework.util.ThreadUtils#initProps()},
     * {@link kr.co.miracom.framework.util.ThreadUtils#removeProps()},
     * {@link kr.co.miracom.framework.util.ThreadUtils#getProp(java.lang.String)} and
     * {@link kr.co.miracom.framework.util.ThreadUtils#setProp(java.lang.String, java.lang.Object)}.
     */
    @Test
    public void testProps() throws Exception {
        Assert.assertEquals("Wrong init status!", false, ThreadUtils.isInit());

        ThreadUtils.setProp("x", "l");
        Assert.assertEquals("setProp function should not work before initProps", null, ThreadUtils.getProp("x"));

        try {
            ThreadUtils.initProps();
            Assert.assertEquals("Wrong init status!", true, ThreadUtils.isInit());

            ThreadUtils.setProp("x", "l");
            Assert.assertEquals("setProp function should work after initProps", "l", ThreadUtils.getProp("x"));

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Assert.assertEquals("Wrong init status!", false, ThreadUtils.isInit());

                    Assert.assertEquals("setProp should not affect to Other Threads", null, ThreadUtils.getProp("x"));
                }

            }, "testSetZoneId-innerThread");
            thread.start();

            thread.join();

            Assert.assertEquals("Wrong init status!", true, ThreadUtils.isInit());

        } finally {
            ThreadUtils.removeProps();
        }

        Assert.assertEquals("Wrong init status!", false, ThreadUtils.isInit());

        Assert.assertEquals("getProp function should return null after removeProps", null, ThreadUtils.getProp("x"));

        ThreadUtils.setProp("x", "l");
        Assert.assertEquals("setProp function should not work after removeProps", null, ThreadUtils.getProp("x"));
    }

}
