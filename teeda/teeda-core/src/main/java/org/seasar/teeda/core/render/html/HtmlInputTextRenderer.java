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

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.util.RendererUtil;
import org.seasar.teeda.core.util.ValueHolderUtil;

/**
 * @author manhole
 */
public class HtmlInputTextRenderer extends AbstractHtmlRenderer {

    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        super.encodeEnd(context, component);
        encodeHtmlInputTextEnd(context, (HtmlInputText) component);
    }

    protected void encodeHtmlInputTextEnd(FacesContext context,
            HtmlInputText htmlInputText) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement(JsfConstants.INPUT_ELEM, htmlInputText);
        RendererUtil.renderAttribute(writer, JsfConstants.TYPE_ATTR,
                JsfConstants.TEXT_VALUE);
        RendererUtil.renderAttribute(writer, JsfConstants.ID_ATTR,
                getIdForRender(context, htmlInputText));
        RendererUtil.renderAttribute(writer, JsfConstants.NAME_ATTR,
                htmlInputText.getClientId(context));

        String value = ValueHolderUtil
                .getValueForRender(context, htmlInputText);
        RendererUtil.renderAttribute(writer, JsfConstants.VALUE_ATTR, value);
        if (htmlInputText.isDisabled()) {
            RendererUtil.renderAttribute(writer, JsfConstants.DISABLED_ATTR,
                    Boolean.TRUE);
        }
        RendererUtil.renderAttributes(writer, htmlInputText,
                JsfConstants.INPUT_PASSTHROUGH_ATTRIBUTES_WITHOUT_DISABLED);
        writer.endElement(JsfConstants.INPUT_ELEM);
    }

}
