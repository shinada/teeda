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

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.teeda.core.render.AbstractRenderer;
import org.seasar.teeda.extension.component.TForEach;

/**
 * @author higa
 * @author manhole
 */
public class TForEachRenderer extends AbstractRenderer {

    public static final String COMPONENT_FAMILY = "org.seasar.teeda.extension.ForEach";

    public static final String RENDERER_TYPE = "org.seasar.teeda.extension.ForEach";

    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        assertNotNull(context, component);
        if (!component.isRendered()) {
            return;
        }
        final TForEach forEach = (TForEach) component;
        final Object page = forEach.getPage(context);
        final BeanDesc beanDesc = BeanDescFactory.getBeanDesc(page.getClass());
        final Object[] items = forEach.getItems(context);
        final String itemName = forEach.getItemName();
        final String indexName = forEach.getIndexName();
        int rowSize = forEach.getRowSize();
        if (rowSize == 0) {
            rowSize = items.length;
        }
        for (int i = 0; i < rowSize; ++i) {
            forEach.enterRow(context, i);
            if (i < items.length) {
                processItem(beanDesc, page, items[i], itemName, i, indexName);
            }
            super.encodeChildren(context, component);
            forEach.leaveRow(context);
        }
    }

    public void processItem(BeanDesc beanDesc, Object page, Object item,
            String itemName, int index, String indexName) {
        setValue(beanDesc, page, indexName, new Integer(index));
        if (item == null) {
            return;
        }
        setValue(beanDesc, page, itemName, item);
        if (item instanceof Map) {
            processMapItem(beanDesc, page, (Map) item);
        } else {
            processBeanItem(beanDesc, page, item);
        }
    }

    protected void setValue(BeanDesc beanDesc, Object page,
            String propertyName, Object value) {
        if (beanDesc.hasPropertyDesc(propertyName)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(propertyName);
            if (pd.hasWriteMethod()) {
                pd.setValue(page, value);
            }
        }
    }

    protected Object getValue(BeanDesc beanDesc, Object page,
            String propertyName) {
        if (beanDesc.hasPropertyDesc(propertyName)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(propertyName);
            if (pd.hasReadMethod()) {
                return pd.getValue(page);
            }
        }
        return null;
    }

    public void processMapItem(BeanDesc beanDesc, Object page, Map item) {
        for (Iterator i = item.keySet().iterator(); i.hasNext();) {
            String name = (String) i.next();
            Object value = item.get(name);
            setValue(beanDesc, page, name, value);
        }
    }

    public void processBeanItem(BeanDesc beanDesc, Object page, Object item) {
        BeanDesc itemBeanDesc = BeanDescFactory.getBeanDesc(item.getClass());
        for (int i = 0; i < itemBeanDesc.getPropertyDescSize(); i++) {
            PropertyDesc pd = itemBeanDesc.getPropertyDesc(i);
            String name = pd.getPropertyName();
            Object value = getValue(itemBeanDesc, item, name);
            setValue(beanDesc, page, name, value);
        }
    }

    public void decode(FacesContext context, UIComponent component) {
        assertNotNull(context, component);
        final TForEach forEach = (TForEach) component;
        final int rowSize = getRowSize(context, forEach);
        forEach.setRowSize(rowSize);
        for (int i = 0; i < rowSize; i++) {
            forEach.enterRow(context, i);
            decodeChildren(context, component);
            forEach.leaveRow(context);
        }
    }

    private int getRowSize(FacesContext context, final TForEach forEach) {
        final Map reqParam = context.getExternalContext()
                .getRequestParameterMap();
        final String namingPrefix = forEach.getClientId(context)
                + NamingContainer.SEPARATOR_CHAR;
        int rowSize = -1;
        for (final Iterator it = reqParam.keySet().iterator(); it.hasNext();) {
            final String name = (String) it.next();
            if (name.startsWith(namingPrefix)) {
                final int pos = name.indexOf(NamingContainer.SEPARATOR_CHAR,
                        namingPrefix.length());
                if (-1 < pos) {
                    String num = name.substring(namingPrefix.length(), pos);
                    try {
                        int index = Integer.parseInt(num);
                        rowSize = Math.max(rowSize, index);
                    } catch (NumberFormatException e) {
                        // TODO
                        e.printStackTrace();
                    }
                }
            }
        }
        rowSize++;
        return rowSize;
    }

    private void decodeChildren(FacesContext context, UIComponent component) {
        for (final Iterator it = component.getChildren().iterator(); it
                .hasNext();) {
            final UIComponent child = (UIComponent) it.next();
            child.processDecodes(context);
        }
    }

    public boolean getRendersChildren() {
        return true;
    }

}
