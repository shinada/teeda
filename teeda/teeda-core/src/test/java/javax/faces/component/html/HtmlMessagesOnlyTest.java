/*
 * Copyright 2004-2011 the Seasar Foundation and the Others.
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
package javax.faces.component.html;

import junit.framework.TestCase;

/**
 * @author manhole
 */
public class HtmlMessagesOnlyTest extends TestCase {

    public void testConstants() throws Exception {
        assertEquals("javax.faces.HtmlMessages", HtmlMessages.COMPONENT_TYPE);
    }

    public void testGetComponentFamily() {
        assertEquals("javax.faces.Messages", new HtmlMessages().getFamily());
    }

    public void testDefaultRendererType() throws Exception {
        assertEquals("javax.faces.Messages", new HtmlMessages()
                .getRendererType());
    }

}
