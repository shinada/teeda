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
package org.seasar.teeda.extension.render;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.render.AbstractRendererTeedaTest;
import javax.faces.render.Renderer;

import org.seasar.framework.container.S2Container;
import org.seasar.teeda.core.el.ELParser;
import org.seasar.teeda.core.el.impl.ValueBindingImpl;
import org.seasar.teeda.core.el.impl.commons.CommonsELParser;
import org.seasar.teeda.core.el.impl.commons.CommonsExpressionProcessorImpl;
import org.seasar.teeda.core.mock.MockUIViewRoot;
import org.seasar.teeda.core.render.html.HtmlInputTextRenderer;
import org.seasar.teeda.core.render.html.HtmlOutputTextRenderer;
import org.seasar.teeda.core.render.html.MockHtmlInputText;
import org.seasar.teeda.core.render.html.MockHtmlOutputText;

/**
 * @author manhole
 */
public class TForEachRendererTeedaTest extends AbstractRendererTeedaTest {

    private S2Container container;

    private TForEachRenderer renderer;

    private MockTForEach forEach;

    protected void setUp() throws Exception {
        super.setUp();
        renderer = createTForEachRenderer();
        forEach = new MockTForEach();
        forEach.setRenderer(renderer);
    }

    public void testEncode1() throws Exception {
        // ## Arrange ##
        final HtmlOutputTextRenderer outputTextRenderer = new HtmlOutputTextRenderer();
        final FacesContext context = getFacesContext();

        final String pageName = "fooPage";
        FooPage page = new FooPage();
        container.register(page, pageName);
        forEach.setPageName(pageName);
        forEach.setItemsName("barItems");

        // items
        {
            Bar[] items = new Bar[3];
            items[0] = new Bar("11");
            items[1] = new Bar("22");
            items[2] = new Bar("33");
            page.setBarItems(items);
        }

        {
            MockHtmlOutputText text = new MockHtmlOutputText();
            text.setRenderer(outputTextRenderer);
            text.setValueBinding("value",
                    createValueBinding("#{fooPage.bar.aaa}"));
            forEach.getChildren().add(text);
        }

        // ## Act ##
        encodeComponent(context, forEach);

        // ## Assert ##
        assertEquals("112233", getResponseText());
    }

    public void testEncode2() throws Exception {
        // ## Arrange ##
        final HtmlInputTextRenderer inputTextRenderer = new HtmlInputTextRenderer();
        final FacesContext context = getFacesContext();

        {
            final String pageName = "fooPage";
            FooPage page = new FooPage();
            container.register(page, pageName);
            forEach.setPageName(pageName);
            forEach.setItemsName("barItems");
            forEach.setId("a");

            // items
            Bar[] items = new Bar[3];
            items[0] = new Bar("111");
            items[1] = new Bar("222");
            items[2] = new Bar("333");
            page.setBarItems(items);
        }

        {
            MockHtmlInputText text = new MockHtmlInputText();
            text.setId("z");
            text.setRenderer(inputTextRenderer);
            text.setValueBinding("value",
                    createValueBinding("#{fooPage.bar.aaa}"));
            forEach.getChildren().add(text);
        }

        // ## Act ##
        encodeComponent(context, forEach);

        // ## Assert ##
        assertEquals(
                "<input type=\"text\" id=\"z\" name=\"a:0:z\" value=\"111\" />"
                        + "<input type=\"text\" id=\"z\" name=\"a:1:z\" value=\"222\" />"
                        + "<input type=\"text\" id=\"z\" name=\"a:2:z\" value=\"333\" />",
                getResponseText());
    }

    private ValueBinding createValueBinding(final String el) {
        final FacesContext context = getFacesContext();
        ELParser parser = new CommonsELParser();
        parser.setExpressionProcessor(new CommonsExpressionProcessorImpl());
        ValueBinding vb = new ValueBindingImpl(context.getApplication(), el,
                parser);
        return vb;
    }

    public void testDecode() throws Exception {
        // ## Arrange ##
        MockUIViewRoot mockUIViewRoot = new MockUIViewRoot();
        mockUIViewRoot.getChildren().add(forEach);

        final HtmlInputTextRenderer inputTextRenderer = new HtmlInputTextRenderer();
        final FacesContext context = getFacesContext();

        FooPage page = new FooPage();
        {
            final String pageName = "fooPage";
            container.register(page, pageName);
            forEach.setPageName(pageName);
            forEach.setItemsName("barItems");
            forEach.setId("a");
        }

        {
            MockHtmlInputText text = new MockHtmlInputText();
            text.setId("z");
            text.setRenderer(inputTextRenderer);
            text.setValueBinding("value",
                    createValueBinding("#{fooPage.bar.aaa}"));
            forEach.getChildren().add(text);
        }

        // input parameters
        {
            final Map req = context.getExternalContext()
                    .getRequestParameterMap();
            req.put("a:0:z", "A");
            req.put("a:1:z", "B");
            req.put("a:2:z", "C");
        }

        // ## Act ##
        forEach.decode(context);
        forEach.processValidators(context);
        forEach.processUpdates(context);

        // ## Assert ##
        final Bar[] items = page.getBarItems();
        assertEquals(3, items.length);
        assertEquals("A", items[0].getAaa());
        assertEquals("B", items[1].getAaa());
        assertEquals("C", items[2].getAaa());
    }

    private TForEachRenderer createTForEachRenderer() {
        return (TForEachRenderer) createRenderer();
    }

    protected Renderer createRenderer() {
        TForEachRenderer renderer = new TForEachRenderer();
        //renderer.setComponentIdLookupStrategy(getComponentIdLookupStrategy());
        return renderer;
    }

    public static class FooPage {

        private Bar bar;

        private Bar[] barItems;

        public Bar[] getBarItems() {
            return barItems;
        }

        public void setBarItems(Bar[] hogeItems) {
            this.barItems = hogeItems;
        }

        public Bar getBar() {
            return bar;
        }

        public void setBar(Bar bar) {
            this.bar = bar;
        }

    }

    public static class Bar {

        public Bar() {
        }

        public Bar(String aaa) {
            setAaa(aaa);
        }

        private String aaa;

        public String getAaa() {
            return aaa;
        }

        public void setAaa(String aaa) {
            this.aaa = aaa;
        }

        public String toString() {
            return "Bar{aaa=" + aaa + "}";
        }
    }

}
