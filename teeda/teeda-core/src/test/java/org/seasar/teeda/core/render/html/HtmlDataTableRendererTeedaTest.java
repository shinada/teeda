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

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIColumn;
import javax.faces.el.ValueBinding;
import javax.faces.render.Renderer;
import javax.faces.render.RendererTeedaTest;

import org.custommonkey.xmlunit.Diff;
import org.seasar.teeda.core.el.impl.ValueBindingImpl;
import org.seasar.teeda.core.el.impl.commons.CommonsELParser;
import org.seasar.teeda.core.mock.MockFacesContext;

/**
 * @author manhole
 */
public class HtmlDataTableRendererTeedaTest extends RendererTeedaTest {

    private HtmlDataTableRenderer renderer_;

    private MockHtmlDataTable htmlDataTable_;

    protected void setUp() throws Exception {
        super.setUp();
        renderer_ = createHtmlDataTableRenderer();
        htmlDataTable_ = new MockHtmlDataTable();
        htmlDataTable_.setRenderer(renderer_);
    }

    public void testEncodeChildren1() throws Exception {
        HtmlOutputTextRenderer htmlOutputTextRenderer = new HtmlOutputTextRenderer();
        htmlDataTable_.setValue(new String[] { "a", "b", "c" });
        htmlDataTable_.setVar("fooVar");
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            htmlOutputText.setValue("Z");
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            ValueBinding vb = new ValueBindingImpl(getApplication(),
                    "#{fooVar}", new CommonsELParser());
            htmlOutputText.setValueBinding("value", vb);
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }

        MockFacesContext context = getFacesContext();

        // ## Act ##
        renderer_.encodeChildren(context, htmlDataTable_);

        // ## Assert ##
        assertEquals("<tbody>" + "<tr><td>Z</td><td>a</td></tr>"
                + "<tr><td>Z</td><td>b</td></tr>"
                + "<tr><td>Z</td><td>c</td></tr>" + "</tbody>",
                getResponseText());
    }

    public void testEncodeChildren_SetFirst() throws Exception {
        HtmlOutputTextRenderer htmlOutputTextRenderer = new HtmlOutputTextRenderer();
        htmlDataTable_.setValue(new String[] { "a", "b", "c" });
        htmlDataTable_.setVar("fooVar");
        htmlDataTable_.setFirst(1);
        {
            UIColumn col = new UIColumn();
            col.setRendered(false);
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            htmlOutputText.setValue("Z");
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            ValueBinding vb = new ValueBindingImpl(getApplication(),
                    "#{fooVar}", new CommonsELParser());
            htmlOutputText.setValueBinding("value", vb);
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }

        MockFacesContext context = getFacesContext();

        // ## Act ##
        renderer_.encodeChildren(context, htmlDataTable_);

        // ## Assert ##
        assertEquals("<tbody>" + "<tr><td>b</td></tr>" + "<tr><td>c</td></tr>"
                + "</tbody>", getResponseText());
    }

    public void testEncodeChildren_SetRows() throws Exception {
        HtmlOutputTextRenderer htmlOutputTextRenderer = new HtmlOutputTextRenderer();
        htmlDataTable_.setValue(new String[] { "a", "b", "c", "d" });
        htmlDataTable_.setVar("fooVar");
        htmlDataTable_.setRows(2);
        {
            UIColumn col = new UIColumn();
            col.setRendered(false);
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            htmlOutputText.setValue("Z");
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            ValueBinding vb = new ValueBindingImpl(getApplication(),
                    "#{fooVar}", new CommonsELParser());
            htmlOutputText.setValueBinding("value", vb);
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }

        MockFacesContext context = getFacesContext();

        // ## Act ##
        renderer_.encodeChildren(context, htmlDataTable_);

        // ## Assert ##
        assertEquals("<tbody>" + "<tr><td>a</td></tr>" + "<tr><td>b</td></tr>"
                + "</tbody>", getResponseText());
    }

    public void testEncodeChildren_RowAndColumnStyle() throws Exception {
        HtmlOutputTextRenderer htmlOutputTextRenderer = new HtmlOutputTextRenderer();
        htmlDataTable_.setValue(new String[] { "a", "b", "c", "d", "e" });
        htmlDataTable_.setVar("fooVar");
        htmlDataTable_.setColumnClasses("c1, c2, c3");
        htmlDataTable_.setRowClasses("r1, r2");
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            htmlOutputText.setValue("Z");
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            htmlOutputText.setValue("Y");
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            htmlOutputText.setValue("X");
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            ValueBinding vb = new ValueBindingImpl(getApplication(),
                    "#{fooVar}", new CommonsELParser());
            htmlOutputText.setValueBinding("value", vb);
            col.getChildren().add(htmlOutputText);
            htmlDataTable_.getChildren().add(col);
        }

        MockFacesContext context = getFacesContext();

        // ## Act ##
        renderer_.encodeChildren(context, htmlDataTable_);

        // ## Assert ##
        assertEquals(
                "<tbody>"
                        + "<tr class=\"r1\">"
                        + "<td class=\"c1\">Z</td><td class=\"c2\">Y</td><td class=\"c3\">X</td><td class=\"c1\">a</td>"
                        + "</tr>"
                        + "<tr class=\"r2\">"
                        + "<td class=\"c1\">Z</td><td class=\"c2\">Y</td><td class=\"c3\">X</td><td class=\"c1\">b</td>"
                        + "</tr>"
                        + "<tr class=\"r1\">"
                        + "<td class=\"c1\">Z</td><td class=\"c2\">Y</td><td class=\"c3\">X</td><td class=\"c1\">c</td>"
                        + "</tr>"
                        + "<tr class=\"r2\">"
                        + "<td class=\"c1\">Z</td><td class=\"c2\">Y</td><td class=\"c3\">X</td><td class=\"c1\">d</td>"
                        + "</tr>"
                        + "<tr class=\"r1\">"
                        + "<td class=\"c1\">Z</td><td class=\"c2\">Y</td><td class=\"c3\">X</td><td class=\"c1\">e</td>"
                        + "</tr>" + "</tbody>", getResponseText());
    }

    public void testEncodeBegin_WithAllAttributes() throws Exception {
        // attributes
        {
            htmlDataTable_.setBgcolor("a");
            htmlDataTable_.setBorder(3);
            htmlDataTable_.setCellpadding("c");
            htmlDataTable_.setCellspacing("d");
            htmlDataTable_.setColumnClasses("e1, e2");
            htmlDataTable_.setDir("f");
            htmlDataTable_.setFooterClass("g");
            htmlDataTable_.setFrame("h");
            htmlDataTable_.setHeaderClass("i");
            htmlDataTable_.setLang("j");
            htmlDataTable_.setOnclick("k");
            htmlDataTable_.setOndblclick("l");
            htmlDataTable_.setOnkeydown("m");
            htmlDataTable_.setOnkeypress("n");
            htmlDataTable_.setOnkeyup("o");
            htmlDataTable_.setOnmousedown("p");
            htmlDataTable_.setOnmousemove("q");
            htmlDataTable_.setOnmouseout("r");
            htmlDataTable_.setOnmouseover("s");
            htmlDataTable_.setOnmouseup("t");
            htmlDataTable_.setRowClasses("u1, u2");
            htmlDataTable_.setRules("v");
            htmlDataTable_.setStyle("w");
            htmlDataTable_.setStyleClass("x");
            htmlDataTable_.setSummary("y");
            htmlDataTable_.setTitle("z");
            htmlDataTable_.setWidth("1");
        }
        // table header, footer
        HtmlOutputTextRenderer htmlOutputTextRenderer = new HtmlOutputTextRenderer();
        {
            MockHtmlOutputText header = new MockHtmlOutputText();
            header.setRenderer(htmlOutputTextRenderer);
            header.setValue("tableHeader");
            htmlDataTable_.setHeader(header);

            MockHtmlOutputText footer = new MockHtmlOutputText();
            footer.setRenderer(htmlOutputTextRenderer);
            footer.setValue("tableFooter");
            htmlDataTable_.setFooter(footer);
        }
        htmlDataTable_.setFirst(4);
        htmlDataTable_.setRows(3);
        htmlDataTable_.setVar("barVar");
        htmlDataTable_.setId("A");
        {
            Map m0 = new HashMap();
            Map m1 = new HashMap();
            Map m2 = new HashMap();
            Map m3 = new HashMap();
            Map m4 = new HashMap();
            Map m5 = new HashMap();
            Map m6 = new HashMap();
            Map m7 = new HashMap();
            Map m8 = new HashMap();
            Map m9 = new HashMap();
            m0.put("k1", "a0");
            m1.put("k1", "a1");
            m2.put("k1", "a2");
            m3.put("k1", "a3");
            m4.put("k1", "a4");
            m5.put("k1", "a5");
            m6.put("k1", "a6");
            m7.put("k1", "a7");
            m8.put("k1", "a8");
            m9.put("k1", "a9");
            m0.put("k2", "b0");
            m1.put("k2", "b1");
            m2.put("k2", "b2");
            m3.put("k2", "b3");
            m4.put("k2", "b4");
            m5.put("k2", "b5");
            m6.put("k2", "b6");
            m7.put("k2", "b7");
            m8.put("k2", "b8");
            m9.put("k2", "b9");
            htmlDataTable_.setValue(new Map[] { m0, m1, m2, m3, m4, m5, m6, m7,
                    m8, m9 });
        }
        // columns
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            ValueBinding vb = new ValueBindingImpl(getApplication(),
                    "#{barVar.k1}", new CommonsELParser());
            htmlOutputText.setValueBinding("value", vb);
            col.getChildren().add(htmlOutputText);

            MockHtmlOutputText header = new MockHtmlOutputText();
            header.setRenderer(htmlOutputTextRenderer);
            header.setValue("col1Header");
            col.setHeader(header);

            MockHtmlOutputText footer = new MockHtmlOutputText();
            footer.setRenderer(htmlOutputTextRenderer);
            footer.setValue("col1Footer");
            col.setFooter(footer);

            htmlDataTable_.getChildren().add(col);
        }
        {
            UIColumn col = new UIColumn();
            MockHtmlOutputText htmlOutputText = new MockHtmlOutputText();
            htmlOutputText.setRenderer(htmlOutputTextRenderer);
            ValueBinding vb = new ValueBindingImpl(getApplication(),
                    "#{barVar.k2}", new CommonsELParser());
            htmlOutputText.setValueBinding("value", vb);
            col.getChildren().add(htmlOutputText);

            MockHtmlOutputText header = new MockHtmlOutputText();
            header.setRenderer(htmlOutputTextRenderer);
            header.setValue("col2Header");
            col.setHeader(header);

            MockHtmlOutputText footer = new MockHtmlOutputText();
            footer.setRenderer(htmlOutputTextRenderer);
            footer.setValue("col2Footer");
            col.setFooter(footer);

            htmlDataTable_.getChildren().add(col);
        }

        MockFacesContext context = getFacesContext();
        renderer_.encodeBegin(context, htmlDataTable_);
        renderer_.encodeChildren(context, htmlDataTable_);
        renderer_.encodeEnd(context, htmlDataTable_);

        Diff diff = new Diff(
                "<table"
                        + " id=\"A\""
                        + " bgcolor=\"a\""
                        + " border=\"3\""
                        + " cellpadding=\"c\""
                        + " cellspacing=\"d\""
                        + " dir=\"f\""
                        + " frame=\"h\""
                        + " lang=\"j\""
                        + " onclick=\"k\""
                        + " ondblclick=\"l\""
                        + " onkeydown=\"m\""
                        + " onkeypress=\"n\""
                        + " onkeyup=\"o\""
                        + " onmousedown=\"p\""
                        + " onmousemove=\"q\""
                        + " onmouseout=\"r\""
                        + " onmouseover=\"s\""
                        + " onmouseup=\"t\""
                        + " rules=\"v\""
                        + " style=\"w\""
                        + " class=\"x\""
                        + " summary=\"y\""
                        + " title=\"z\""
                        + " width=\"1\""
                        + ">"

                        + "<thead>"
                        + "<tr>"
                        + "<th class=\"i\" colspan=\"2\" scope=\"colgroup\">tableHeader</th>"
                        + "</tr>"
                        + "<tr>"
                        + "<th colgroup=\"col\" class=\"i\">col1Header</th>"
                        + "<th colgroup=\"col\" class=\"i\">col2Header</th>"
                        + "</tr>"
                        + "</thead>"

                        + "<tfoot>"
                        + "<tr>"
                        + "<td class=\"g\" colspan=\"2\">tableFooter</td>"
                        + "</tr>"
                        + "<tr><td class=\"g\">col1Footer</td>"
                        + "<td class=\"g\">col2Footer</td></tr>"
                        + "</tfoot>"

                        + "<tbody>"
                        + "<tr class=\"u1\"><td class=\"e1\">a4</td><td class=\"e2\">b4</td></tr>"
                        + "<tr class=\"u2\"><td class=\"e1\">a5</td><td class=\"e2\">b5</td></tr>"
                        + "<tr class=\"u1\"><td class=\"e1\">a6</td><td class=\"e2\">b6</td></tr>"
                        + "</tbody>"

                        + "</table>", getResponseText());
        assertEquals(diff.toString(), true, diff.identical());
    }

    private HtmlDataTableRenderer createHtmlDataTableRenderer() {
        return (HtmlDataTableRenderer) createRenderer();
    }

    protected Renderer createRenderer() {
        return new HtmlDataTableRenderer();
    }

}
