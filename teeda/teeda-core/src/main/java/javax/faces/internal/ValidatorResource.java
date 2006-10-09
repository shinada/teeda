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

import java.util.HashMap;
import java.util.Map;

import javax.faces.validator.Validator;

import org.seasar.framework.util.Disposable;
import org.seasar.framework.util.DisposableUtil;

/**
 * @author shot
 * @author higa
 */
public class ValidatorResource {

    private static Map validators_ = new HashMap();

    static {
        DisposableUtil.add(new Disposable() {
            public void dispose() {
                removeAll();
            }
        });
    };

    protected ValidatorResource() {
    }

    public static synchronized Validator getValidator(String expression) {
        return (Validator) validators_.get(expression);
    }

    public static synchronized void addValidator(String expression,
            Validator validator) {
        Validator previous = getValidator(expression);
        if (previous == null) {
            validators_.put(expression, validator);
        } else if (previous instanceof ValidatorChain) {
            ((ValidatorChain) previous).add(validator);
        } else {
            ValidatorChain chain = new ValidatorChain();
            chain.add(previous);
            chain.add(validator);
            validators_.put(expression, chain);
        }
    }

    public static synchronized void removeValidator(String expression) {
        validators_.remove(expression);
    }

    public static void removeAll() {
        validators_.clear();
    }
}
