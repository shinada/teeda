/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.teeda.core.util;

import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

public class DIContainerUtil {

    private static final Logger logger_ = Logger.getLogger(DIContainerUtil.class);

    private DIContainerUtil() {
    }

    public static Object getComponent(Class clazz) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return container.getComponent(clazz);
    }

    public static Object getComponentNoException(Class clazz) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        try {
            return container.getComponent(clazz);
        } catch (ComponentNotFoundRuntimeException e) {
            logger_.warn("Component: " + clazz + " is not found at DIContainer.");
            return null;
        }
    }

}
