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
package org.seasar.teeda.extension.taglib;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;

import org.seasar.teeda.core.taglib.UIComponentTagBase;
import org.seasar.teeda.extension.ExtensionConstants;
import org.seasar.teeda.extension.component.TCondition;

/**
 * @author shot
 */
public class TConditionTag extends UIComponentTagBase {

    public TConditionTag() {
    }

    public String getComponentType() {
        return TCondition.COMPONENT_TYPE;
    }

    public String getRendererType() {
        return "org.seasar.teeda.extension.Condition";
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        TCondition condition = (TCondition) component;
        HtmlInputHidden hidden = new HtmlInputHidden();
        hidden
                .setId(condition.getId()
                        + ExtensionConstants.TEEDA_HIDDEN_SUFFIX);
        condition.getChildren().add(hidden);
    }

}