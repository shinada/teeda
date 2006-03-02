/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.teeda.it.learning.local;

import java.net.URL;

import junit.framework.TestCase;

import org.seasar.framework.util.ResourceNotFoundRuntimeException;
import org.seasar.framework.util.ResourceUtil;

/**
 * @author manhole
 */
public abstract class AbstractLocalTestCase extends TestCase {

    protected URL getFileAsUrl(String s) {
        String fileNameByClass = getClass().getName().replace('.', '/') + "_"
            + s;
        try {
            return ResourceUtil.getResource(fileNameByClass);
        } catch (ResourceNotFoundRuntimeException e) {
            String fileNameByPackage = getClass().getPackage().getName()
                .replace('.', '/')
                + "/" + s;
            return ResourceUtil.getResource(fileNameByPackage);
        }
    }

}
