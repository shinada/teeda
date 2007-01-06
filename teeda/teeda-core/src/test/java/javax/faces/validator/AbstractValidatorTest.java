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
package javax.faces.validator;

import org.seasar.teeda.core.unit.TeedaTestCase;

/**
 * @author shot
 */
public abstract class AbstractValidatorTest extends TeedaTestCase {

    public void testValidate_facesContextIsNull() throws Exception {
        Validator validator = createValidator();
        try {
            validator.validate(null, getFacesContext().getViewRoot(), "hoge");
            fail();
        } catch (NullPointerException expected) {
            success();
        }
    }

    public void testValidate_componentIsNull() throws Exception {
        Validator validator = createValidator();
        try {
            validator.validate(getFacesContext(), null, "hoge");
            fail();
        } catch (NullPointerException expected) {
            success();
        }
    }

    public void testValidate_nullValueIsValid() throws Exception {
        Validator validator = createValidator();
        try {
            validator.validate(getFacesContext(), getFacesContext()
                    .getViewRoot(), null);
            success();
        } catch (NullPointerException expected) {
            fail();
        }
    }

    protected abstract Validator createValidator();
}
