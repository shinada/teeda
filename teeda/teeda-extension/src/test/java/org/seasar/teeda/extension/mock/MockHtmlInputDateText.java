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
package org.seasar.teeda.extension.mock;

import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

import org.seasar.teeda.extension.component.html.THtmlInputDateText;

public class MockHtmlInputDateText extends THtmlInputDateText {

    private Renderer renderer;

    public Renderer getRenderer(FacesContext context) {
        if (this.renderer != null) {
            return this.renderer;
        }
        return super.getRenderer(context);
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

}