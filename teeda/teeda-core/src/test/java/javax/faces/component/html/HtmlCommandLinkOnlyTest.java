/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
public class HtmlCommandLinkOnlyTest extends TestCase {

    public void testConstants() throws Exception {
        assertEquals("javax.faces.HtmlCommandLink",
                HtmlCommandLink.COMPONENT_TYPE);
    }

    public void testGetComponentFamily() {
        HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
        assertEquals("javax.faces.Command", htmlCommandLink.getFamily());
    }

    public void testDefaultRendererType() throws Exception {
        HtmlCommandLink htmlCommandLink = new HtmlCommandLink();
        assertEquals("javax.faces.Link", htmlCommandLink.getRendererType());
    }

}
