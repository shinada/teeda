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
package javax.faces.internal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

/**
 * @author shot
 * 
 * This class might be changed without notice. Please do not use it
 * excluding the JSF specification part.
 */
public class ComponentHtmlUtils {

    private ComponentHtmlUtils() {
    }

    public static Object getValueBindingValue(UIComponent component,
            String bindingName, FacesContext context) {
        ValueBinding vb = component.getValueBinding(bindingName);
        return vb != null ? (String) vb.getValue(context) : null;
    }
}
