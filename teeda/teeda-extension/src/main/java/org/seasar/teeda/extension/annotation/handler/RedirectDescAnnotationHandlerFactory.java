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
package org.seasar.teeda.extension.annotation.handler;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;

/**
 * 
 * @author koichik
 */
public class RedirectDescAnnotationHandlerFactory {

    private static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.teeda.extension.annotation.handler.TigerRedirectDescAnnotationHandler";

    private static RedirectDescAnnotationHandler annotationHandler;

    static {
        Class clazz = ConstantRedirectDescAnnotationHandler.class;
        try {
            clazz = ClassUtil.forName(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
        } catch (final ClassNotFoundRuntimeException ignore) {
        }
        annotationHandler = (RedirectDescAnnotationHandler) ClassUtil
                .newInstance(clazz);
    }

    protected RedirectDescAnnotationHandlerFactory() {
    }

    public static RedirectDescAnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }

    public static void setAnnotationHandler(
            final RedirectDescAnnotationHandler handler) {
        annotationHandler = handler;
    }

}
