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
package org.seasar.teeda.core.context.creator.portlet;

import javax.faces.context.ExternalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.seasar.teeda.core.context.creator.ExternalContextCreator;
import org.seasar.teeda.core.context.portlet.PortletExternalContextImpl;

/**
 * @author shot
 * 
 */
public class PortletExternalContextCreator implements ExternalContextCreator {

    public ExternalContext create(Object context, Object request,
            Object response) {
        if (!PortletEnvironmentUtil.isPortletEnvironment(context, request,
                response)) {
            return null;
        }
        return createPortletContext((PortletContext) context,
                (PortletRequest) request, (PortletResponse) response);
    }

    protected ExternalContext createPortletContext(PortletContext context,
            PortletRequest request, PortletResponse response) {
        return new PortletExternalContextImpl(context, request, response);
    }

}
