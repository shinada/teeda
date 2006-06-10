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
package javax.faces.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.internal.ConvertUtils;

import org.seasar.framework.util.AssertionUtil;

/**
 * @author shot
 */
public class FloatConverter implements Converter {

    public static final String CONVERTER_ID = "javax.faces.Float";

    public FloatConverter() {
    }

    public Object getAsObject(FacesContext context, UIComponent component,
            String value) throws ConverterException {
        AssertionUtil.assertNotNull("FacesContext", context);
        AssertionUtil.assertNotNull("UIComponent", component);
        if (value == null) {
            return null;
        }

        value = value.trim();
        if (value.length() < 1) {
            return null;
        }
        try {
            return Float.valueOf(value);
        } catch (Exception e) {
            Object[] args = ConvertUtils.createExceptionMessageArgs(component,
                    value);
            throw ConvertUtils.wrappedByConverterException(this, context, args,
                    e);
        }
    }

    public String getAsString(FacesContext context, UIComponent component,
            Object value) throws ConverterException {
        AssertionUtil.assertNotNull("FacesContext", context);
        AssertionUtil.assertNotNull("UIComponent", component);
        if (value == null) {
            return "";
        }
        try {
            return (value instanceof String) ? (String) value : (Float
                    .toString(((Float) value).floatValue()));
        } catch (Exception e) {
            throw ConvertUtils.wrappedByConverterException(e);
        }
    }
}
