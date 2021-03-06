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
package org.seasar.teeda.core.config.faces.impl;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ClassLoaderUtil;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.URLUtil;
import org.seasar.framework.xml.SaxHandlerParser;
import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.config.faces.AbstractFacesConfigurator;
import org.seasar.teeda.core.config.faces.element.FacesConfig;
import org.seasar.teeda.core.util.IteratorUtil;

/**
 * @author shot
 */
public class MetaInfFacesConfigurator extends AbstractFacesConfigurator {

    private static final Logger logger_ = Logger
            .getLogger(MetaInfFacesConfigurator.class);

    private String path_ = JsfConstants.WEB_INF_LIB;

    public MetaInfFacesConfigurator() {
    }

    public FacesConfig configure() {
        String path = getPath();
        if (logger_.isDebugEnabled()) {
            logger_.debug("target file path = " + path);
        }
        SaxHandlerParser parser = createSaxHandlerParser();
        List list = new ArrayList();
        ClassLoader loader = ClassLoaderUtil.getClassLoader(this.getClass());
        for (Iterator itr = IteratorUtil.getResourcesIterator(loader,
                JsfConstants.FACES_CONFIG_RESOURCES); itr.hasNext();) {
            URL url = (URL) itr.next();
            if (url.toExternalForm().indexOf(path) != -1) {
                InputStream is = URLUtil.openStream(url);
                try {
                    FacesConfig config = (FacesConfig) parser.parse(is, url
                            .toExternalForm());
                    list.add(config);
                } finally {
                    InputStreamUtil.close(is);
                }
            }
        }
        return FacesConfigUtil.collectAllFacesConfig(list);
    }

    public String getPath() {
        return path_;
    }

    public void setPath(String path) {
        path_ = path;
    }
}