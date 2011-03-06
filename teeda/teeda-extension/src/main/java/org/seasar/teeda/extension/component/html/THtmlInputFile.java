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
package org.seasar.teeda.extension.component.html;

import javax.faces.component.ComponentUtil_;
import javax.faces.context.FacesContext;

import org.seasar.teeda.core.JsfConstants;

/**
 * @author koichik
 */
public class THtmlInputFile extends THtmlInputText {

    public static final String COMPONENT_TYPE = "org.seasar.teeda.extension.HtmlInputFile";

    public static final String DEFAULT_RENDERER_TYPE = "org.seasar.teeda.extension.HtmlInputFile";

    protected String accept;

    public THtmlInputFile() {
    }

    public String getAccept() {
        if (accept != null) {
            return accept;
        }
        return ComponentUtil_.getValueBindingValueAsString(this,
                JsfConstants.ACCEPT_ATTR);
    }

    public void setAccept(final String accept) {
        this.accept = accept;
    }

    public void restoreState(final FacesContext context, final Object state) {
        final Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        accept = (String) values[1];
    }

    public Object saveState(final FacesContext context) {
        final Object[] values = new Object[2];
        values[0] = super.saveState(context);
        values[1] = accept;
        return values;
    }

}
