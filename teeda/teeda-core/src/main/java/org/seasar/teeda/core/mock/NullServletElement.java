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
package org.seasar.teeda.core.mock;

import org.seasar.teeda.core.config.webapp.element.InitParamElement;
import org.seasar.teeda.core.config.webapp.element.ServletElement;

/**
 * @author manhole
 */
public class NullServletElement implements ServletElement {

    public String getLoadOnStartup() {
        return null;
    }

    public String getServletClass() {
        return null;
    }

    public String getServletName() {
        return null;
    }

    public void setLoadOnStartup(final String loadOnStartup) {
    }

    public void setServletClass(final String servletClass) {
    }

    public void setServletName(final String servletName) {
    }

    public void addInitParamElement(final InitParamElement initParam) {
    }

    public InitParamElement getInitParamElementByParamName(
            final String paramName) {
        return null;
    }

}