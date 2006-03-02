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
package org.seasar.teeda.core.render.html;

import javax.faces.render.AbstractRendererTest;

import org.seasar.teeda.core.mock.MockFacesContext;

/**
 * @author manhole
 */
public class HtmlFormAndCommandLinkRendererTest extends AbstractRendererTest {

    private HtmlFormRenderer formRenderer_;

    private HtmlCommandLinkRenderer commandLinkRenderer_;

    private MockHtmlForm htmlForm_;

    protected void setUp() throws Exception {
        super.setUp();
        formRenderer_ = new HtmlFormRenderer();
        htmlForm_ = new MockHtmlForm();
        htmlForm_.setRenderer(formRenderer_);
        htmlForm_.setEnctype(null);

        commandLinkRenderer_ = new HtmlCommandLinkRenderer();
    }

    public void test1() throws Exception {
        // ## Arrange ##
        MockHtmlCommandLink htmlCommandLink = new MockHtmlCommandLink();
        htmlCommandLink.setRenderer(commandLinkRenderer_);
        htmlCommandLink.setId("fooLink");

        htmlForm_.setId("fooForm");
        htmlForm_.getChildren().add(htmlCommandLink);

        MockFacesContext context = getFacesContext();

        // <input type="hidden" name="fooForm:__link_clicked__" />

        // ## Act ##
        formRenderer_.encodeBegin(context, htmlForm_);
        formRenderer_.encodeChildren(context, htmlForm_);
        formRenderer_.encodeEnd(context, htmlForm_);

        // ## Assert ##
        assertEquals(
                "<form id=\"fooForm\" name=\"fooForm\" method=\"post\""
                        + " enctype=\"application/x-www-form-urlencoded\">"
                        + "<a"
                        + " id=\"fooLink\""
                        + " href=\"#\""
                        + " onclick=\""
                        + "var f = document.forms['fooForm'];"
                        + " f['fooForm:__link_clicked__'].value = 'fooForm:fooLink';"
                        + " if (f.onsubmit) { f.onsubmit(); }"
                        + " f.submit();"
                        + " return false;"
                        + "\"></a>"
                        + "<input type=\"hidden\" name=\"fooForm\" value=\"fooForm\" />"
                        + "<input type=\"hidden\" name=\"fooForm:__link_clicked__\" />"
                        + "</form>", getResponseText());
    }

}
