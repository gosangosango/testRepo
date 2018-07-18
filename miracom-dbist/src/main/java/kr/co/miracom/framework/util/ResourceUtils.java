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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author Steve M. Jung
 * @since 2012. 2. 16. (version 1.0.6)
 */
public class ResourceUtils {
    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();

    public static boolean isDirectory(String path) {
        ValueUtils.assertNotNull("path", path);
        path = path.trim();

        if (path.startsWith("classpath*:")) {
            return isDirectoryByClasspath(path.substring(11));
        } else if (path.startsWith("classpath:")) {
            return isDirectoryByClasspath(path.substring(10));
        }

        File file;
        try {
            file = org.springframework.util.ResourceUtils.getFile(path);
        } catch (FileNotFoundException e) {
            return isDirectoryByClasspath(path);
        }
        return file.exists() ? file.isDirectory() : isDirectoryByClasspath(path);
    }

    private static boolean isDirectoryByClasspath(String classpath) {
        classpath = classpath.trim();
        Resource resource = new ClassPathResource(classpath);
        if (resource.exists()) {
            try {
                return resource.contentLength() == 0;
            } catch (IOException e) {
                return false;
            }
        }
        try {
            classpath = "classpath*:" + classpath + (classpath.endsWith("/") ? "*" : "/*");
            Resource[] resources = RESOURCE_PATTERN_RESOLVER.getResources(classpath);
            return !ValueUtils.isEmpty(resources);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean exists(String path) {
        ValueUtils.assertNotNull("path", path);
        path = path.trim();

        if (path.startsWith("classpath*:")) {
            return existsByClasspath(path.substring(11));
        } else if (path.startsWith("classpath:")) {
            return existsByClasspath(path.substring(10));
        }

        File file;
        try {
            file = org.springframework.util.ResourceUtils.getFile(path);
        } catch (FileNotFoundException e) {
            return existsByClasspath(path);
        }
        return file.exists() || existsByClasspath(path);
    }

    private static boolean existsByClasspath(String classpath) {
        classpath = classpath.trim();
        Resource resource = new ClassPathResource(classpath);
        if (resource.exists())
            return true;
        try {
            classpath = "classpath*:" + classpath + (classpath.endsWith("/") ? "*" : "/*");
            Resource[] resources = RESOURCE_PATTERN_RESOLVER.getResources(classpath);
            return !ValueUtils.isEmpty(resources);
        } catch (IOException e) {
            return false;
        }
    }

    public static String readText(String path) throws IOException {
        ValueUtils.assertNotNull("path", path);
        path = path.trim();

        if (path.startsWith("classpath*:")) {
            path = path.substring(11);
            return readTextByClasspath(path);
        } else if (path.startsWith("classpath:")) {
            path = path.substring(10);
            return readTextByClasspath(path);
        }

        File file;
        try {
            file = org.springframework.util.ResourceUtils.getFile(path);
        } catch (FileNotFoundException e) {
            try {
                return readTextByClasspath(path);
            } catch (IOException e1) {
            }
            throw e;
        }
        if (file.exists())
            return FileUtils.readFileToString(file, "UTF-8");

        try {
            return readTextByClasspath(path);
        } catch (IOException e1) {
            throw new FileNotFoundException("path: " + path);
        }
    }

    private static String readTextByClasspath(String classpath) throws IOException {
        classpath = classpath.trim();
        Resource resource = new ClassPathResource(classpath);
        if (resource.exists())
            return IOUtils.toString(resource.getInputStream(), "UTF-8");
        if (isDirectoryByClasspath(classpath))
            throw new IllegalArgumentException("Cannot read directory as text by classpath: " + classpath);
        throw new IllegalArgumentException("Couldn't find resource by classpath: " + classpath);
    }
}
