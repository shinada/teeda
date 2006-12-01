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
package org.seasar.teeda.extension.render.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.internal.IgnoreAttribute;

import org.seasar.framework.util.StringUtil;
import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.render.AbstractInputRenderer;
import org.seasar.teeda.core.render.EncodeConverter;
import org.seasar.teeda.core.util.RendererUtil;
import org.seasar.teeda.extension.component.TreeNode;
import org.seasar.teeda.extension.component.html.THtmlInputHidden;
import org.seasar.teeda.extension.component.html.THtmlTreeHidden;
import org.seasar.teeda.extension.util.ComponentHolder;

/**
 * @author shot
 */
public class THtmlTreeHiddenRenderer extends AbstractInputRenderer {

    public static final String COMPONENT_FAMILY = THtmlTreeHidden.COMPONENT_FAMILY;

    public static final String RENDERER_TYPE = THtmlTreeHidden.DEFAULT_RENDERER_TYPE;

    private EncodeConverter encodeConverter;

    private final IgnoreAttribute ignoreComponent = new IgnoreAttribute();
    {
        ignoreComponent.addAttributeName(JsfConstants.ID_ATTR);
        ignoreComponent.addAttributeName(JsfConstants.VALUE_ATTR);
    }

    public void decode(FacesContext context, UIComponent component) {
        assertNotNull(context, component);
        getDecoder().decode(context, component);
        decodeTHtmlTreeHidden(context, (THtmlTreeHidden) component);
    }

    protected void decodeTHtmlTreeHidden(FacesContext context,
            THtmlTreeHidden hidden) {
        String s = (String) hidden.getSubmittedValue();
        if (StringUtil.isEmpty(s)) {
            return;
        }
        final ComponentHolder holder = (ComponentHolder) deserialize(s);
        final String componentClassName = holder.getComponentClassName();
        if (componentClassName == null) {
            return;
        }
        final List restoredList = holder.getValue();
        hidden.setValue(restoredList.get(0));
    }

    public void encodeEnd(final FacesContext context,
            final UIComponent component) throws IOException {
        assertNotNull(context, component);
        if (!component.isRendered()) {
            return;
        }
        encodeHtmlTreeHiddenEnd(context, (THtmlTreeHidden) component);
    }

    protected void encodeHtmlTreeHiddenEnd(final FacesContext context,
            final THtmlTreeHidden htmlTreeHidden) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.startElement(JsfConstants.INPUT_ELEM, htmlTreeHidden);
        RendererUtil.renderAttribute(writer, JsfConstants.TYPE_ATTR,
                JsfConstants.HIDDEN_VALUE);
        RendererUtil.renderIdAttributeIfNecessary(writer, htmlTreeHidden,
                getIdForRender(context, htmlTreeHidden));
        RendererUtil.renderAttribute(writer, JsfConstants.NAME_ATTR,
                htmlTreeHidden.getClientId(context));

        final String value = getValueForRender(context, htmlTreeHidden);
        RendererUtil.renderAttribute(writer, JsfConstants.VALUE_ATTR, value);
        renderRemainAttributes(htmlTreeHidden, writer, ignoreComponent);

        writer.endElement(JsfConstants.INPUT_ELEM);
    }

    protected String getValueForRender(FacesContext context,
            THtmlInputHidden htmlInputHidden) {
        final Object submittedValue = htmlInputHidden.getSubmittedValue();
        if (submittedValue != null) {
            if (submittedValue instanceof String) {
                return (String) submittedValue;
            }
            return submittedValue.toString();
        }
        final Object value = htmlInputHidden.getValue();
        if (value == null) {
            return "";
        }
        ComponentHolder holder = buildComponentHolder(value);
        if (holder == null) {
            throw new IllegalArgumentException();
        }
        return serialize(holder);
    }

    private static ComponentHolder buildComponentHolder(Object value) {
        if (value == null) {
            return null;
        }
        ComponentHolder holder = null;
        if (value instanceof TreeNode) {
            holder = buildTreeNodeTypeHolder((TreeNode) value);
        }
        return holder;
    }

    private static ComponentHolder buildTreeNodeTypeHolder(TreeNode node) {
        final List list = new ArrayList();
        list.add(node);
        //list.addAll(node.getChildren());
        ComponentHolder holder = new ComponentHolder();
        holder.setComponentClassName(node.getClass().getName());
        holder.setValue(list);
        return holder;
    }

    protected String serialize(final Object target) {
        return encodeConverter.getAsEncodeString(target);
    }

    protected Object deserialize(final String value) {
        return encodeConverter.getAsDecodeObject(value);
    }

    public EncodeConverter getEncodeConverter() {
        return encodeConverter;
    }

    public void setEncodeConverter(EncodeConverter encodeConverter) {
        this.encodeConverter = encodeConverter;
    }

}