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
package org.seasar.teeda.extension.validator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.internal.FacesMessageUtil;
import javax.faces.internal.UIComponentUtil;
import javax.faces.internal.UIInputUtil;
import javax.faces.validator.Validator;

import org.seasar.framework.util.AssertionUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.teeda.extension.exception.ExtendValidatorException;
import org.seasar.teeda.extension.util.ValidatorUtil;

/**
 * @author higa
 * @author shot
 */
public class TRequiredValidator implements Validator, StateHolder {

    public static final String VALIDATOR_ID = "javax.faces.Required";

    public static final String REQUIRED_MESSAGE_ID = "javax.faces.component.UIInput.REQUIRED";

    private boolean transientValue = false;

    private String forValue;

    private String[] forValues;

    private String messageId;

    public void validate(FacesContext context, UIComponent component,
            Object value) throws FacesException {
        AssertionUtil.assertNotNull("context", context);
        AssertionUtil.assertNotNull("component", component);
        if (!ValidatorUtil.isTargetCommand(context, forValues)) {
            return;
        }
        if (UIInputUtil.isEmpty(value)) {
            Object[] args = new Object[] { UIComponentUtil.getLabel(component) };
            String msgId = (getMessageId() != null) ? getMessageId()
                    : REQUIRED_MESSAGE_ID;
            FacesMessage message = FacesMessageUtil.getMessage(context, msgId,
                    args);
            throw new ExtendValidatorException(message, new String[]{msgId});
        }
    }

    public boolean isTransient() {
        return transientValue;
    }

    public void setTransient(boolean transientValue) {
        this.transientValue = transientValue;
    }

    public String getFor() {
        return forValue;
    }

    public void setFor(String forValue) {
        this.forValue = forValue;
        forValues = StringUtil.split(forValue, ", ");
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Object saveState(FacesContext context) {
        Object[] values = new Object[2];
        values[0] = forValue;
        values[1] = messageId;
        return values;
    }

    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[]) state;
        setFor((String) values[0]);
        messageId = (String) values[1];
    }
}
