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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.LongRangeValidator;
import javax.faces.validator.ValidatorException;

import org.seasar.framework.util.AssertionUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.teeda.extension.exception.ExtendValidatorException;
import org.seasar.teeda.extension.util.ValidatorUtil;

/**
 * @author shot
 */
public class TLongRangeValidator extends LongRangeValidator {

    private String forValue;

    private String[] forValues;

    private String maximumMessageId;

    private String minimumMessageId;

    private String notInRangeMessageId;

    private String typeMessageId;

    public TLongRangeValidator() {
        super();
    }

    public TLongRangeValidator(long maximum) {
        super(maximum);
    }

    public TLongRangeValidator(long maximum, long minimum) {
        super(maximum, minimum);
    }

    public void validate(FacesContext context, UIComponent component,
            Object value) throws FacesException {
        AssertionUtil.assertNotNull("context", context);
        AssertionUtil.assertNotNull("component", component);
        if (!ValidatorUtil.isTargetCommand(context, forValues)) {
            return;
        }
        try {
            super.validate(context, component, value);
        } catch (ValidatorException e) {
            throw new ExtendValidatorException(e.getFacesMessage(), e,
                    new String[] { maximumMessageId, minimumMessageId,
                            notInRangeMessageId, typeMessageId });
        }
    }

    public Object saveState(FacesContext context) {
        Object[] state = new Object[6];
        state[0] = super.saveState(context);
        state[1] = forValue;
        state[2] = maximumMessageId;
        state[3] = minimumMessageId;
        state[4] = notInRangeMessageId;
        state[5] = typeMessageId;
        return state;
    }

    public void restoreState(FacesContext context, Object obj) {
        Object[] state = (Object[]) obj;
        super.restoreState(context, state[0]);
        forValue = (String) state[1];
        setFor(forValue);
        maximumMessageId = (String) state[2];
        minimumMessageId = (String) state[3];
        notInRangeMessageId = (String) state[4];
        typeMessageId = (String) state[5];
    }

    public String getFor() {
        return forValue;
    }

    public void setFor(String forValue) {
        this.forValue = forValue;
        forValues = StringUtil.split(forValue, ", ");
    }

    public String getMaximumMessageId() {
        return (maximumMessageId != null) ? maximumMessageId
                : MAXIMUM_MESSAGE_ID;
    }

    public String getMinimumMessageId() {
        return (minimumMessageId != null) ? minimumMessageId
                : MINIMUM_MESSAGE_ID;
    }

    public String getNotInRangeMessageId() {
        return (notInRangeMessageId != null) ? notInRangeMessageId
                : NOT_IN_RANGE_MESSAGE_ID;
    }

    public String getTypeMessageId() {
        return (typeMessageId != null) ? typeMessageId : TYPE_MESSAGE_ID;
    }

    public void setMaximumMessageId(String maximumMessageId) {
        this.maximumMessageId = maximumMessageId;
    }

    public void setMinimumMessageId(String minimumMessageId) {
        this.minimumMessageId = minimumMessageId;
    }

    public void setNotInRangeMessageId(String notInRangeMessageId) {
        this.notInRangeMessageId = notInRangeMessageId;
    }

    public void setTypeMessageId(String typeMessageId) {
        this.typeMessageId = typeMessageId;
    }

}
