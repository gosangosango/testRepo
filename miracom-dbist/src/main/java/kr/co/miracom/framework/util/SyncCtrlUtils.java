/**
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.co.miracom.framework.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steve M. Jung
 * @since 2011. 6. 9. (version 0.0.1)
 */
public class SyncCtrlUtils {
    private static final Logger logger = LoggerFactory.getLogger(SyncCtrlUtils.class);

    public static <V, E extends Throwable> V wrap(String name, Closure<V, E> closure) throws E {
        synchronized (get(name)) {
            try {
                return closure.execute();
            } finally {
                release(name);
            }
        }
    }

    public static <K, V, E extends Throwable> V wrap(final String name, final Map<K, V> cache, final K key,
                    final Closure<V, E> closure) throws E {
        ValueUtils.assertNotNull("name", name);
        ValueUtils.assertNotNull("cache", cache);

        final boolean debug = logger.isDebugEnabled();

        if (key == null) {
            if (debug)
                logger.debug("key is null, so execute closure right away.");
            return (V) wrap(name, closure);
        }

        if (cache.containsKey(key)) {
            if (debug)
                logger.debug("get data from map cache by key: " + key);
            return cache.get(key);
        }

        return wrap(name, new Closure<V, E>() {
            public V execute() throws E {
                if (cache.containsKey(key)) {
                    if (debug)
                        logger.debug("get data from map cache by key: " + key);
                    return cache.get(key);
                }

                if (debug)
                    logger.debug("get data by executing closure.");
                V value = closure.execute();
                if (value != null) {
                    if (debug)
                        logger.debug("put data to map cache by key: " + key);
                    cache.put(key, value);
                }
                return value;
            }
        });
    }

    private static Map<String, Monitor> cache = new ConcurrentHashMap<String, Monitor>();

    public static Object get(String name) {
        ValueUtils.assertNotNull("name", name);

        final boolean debug = logger.isDebugEnabled();

        synchronized (cache) {
            Monitor monitor = null;
            try {
                if (cache.containsKey(name)) {
                    monitor = cache.get(name);
                    monitor.i++;
                } else {
                    monitor = Monitor.newInstance();
                    cache.put(name, monitor);
                }
            } finally {
                if (debug)
                    logger.debug("get monitor name: " + name + ", index: " + monitor.i);
            }
            return monitor;
        }
    }

    public static void release(String name) {
        ValueUtils.assertNotNull("name", name);

        final boolean debug = logger.isDebugEnabled();

        synchronized (cache) {
            if (!cache.containsKey(name)) {
                if (debug)
                    logger.debug("the monitor was not released because there was not any monitor with name: " + name);
                return;
            }

            Monitor monitor = cache.get(name);
            if (monitor.i > 0) {
                if (debug)
                    logger.debug("release monitor name: " + name + ", index: " + monitor.i);
                monitor.i--;
                return;
            }

            if (debug)
                logger.debug("remove monitor name: " + name + ", index: " + monitor.i);
            cache.remove(name);
        }
    }

    static class Monitor {
        static Monitor newInstance() {
            return new Monitor();
        }

        int i = 0;
    }
}
