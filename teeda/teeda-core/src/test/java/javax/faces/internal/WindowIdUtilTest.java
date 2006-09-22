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
package javax.faces.internal;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author higa
 * 
 */
public class WindowIdUtilTest extends TestCase {

    public void testCreateWindowId() throws Exception {
        String wid = WindowIdUtil.createWindowId();
        System.out.println(wid);
        assertNotNull(wid);
        String wid2 = WindowIdUtil.createWindowId();
        System.out.println(wid2);
        assertFalse(wid.equals(wid2));
    }

    public void testIsNewWindowTarget() throws Exception {
        assertTrue(WindowIdUtil.isNewWindowTarget("_blank"));
        assertTrue(WindowIdUtil.isNewWindowTarget("hoge"));
        assertFalse(WindowIdUtil.isNewWindowTarget("_self"));
        assertFalse(WindowIdUtil.isNewWindowTarget(""));
    }

    public void testNeedNewWindow() throws Exception {
        Map parameterMap = new HashMap();
        assertFalse(WindowIdUtil.needNewWindow(parameterMap));
        parameterMap.put(WindowIdUtil.NEWWINDOW, "false");
        assertFalse(WindowIdUtil.needNewWindow(parameterMap));
        parameterMap.put(WindowIdUtil.NEWWINDOW, "true");
        assertTrue(WindowIdUtil.needNewWindow(parameterMap));
    }
}