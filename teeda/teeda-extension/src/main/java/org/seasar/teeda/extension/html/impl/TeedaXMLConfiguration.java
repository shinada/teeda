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
package org.seasar.teeda.extension.html.impl;

import org.apache.xerces.parsers.XML11Configuration;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLComponent;

/**
 * @author shot
 *
 */
public class TeedaXMLConfiguration extends XML11Configuration {

    public TeedaXMLConfiguration() {
        TeedaXMLDocumentScannerImpl scanner = new TeedaXMLDocumentScannerImpl();
        setProperty(DOCUMENT_SCANNER, scanner);
        addComponent((XMLComponent) scanner);
        fEntityManager.setEntityHandler(scanner);
        fNamespaceScanner = scanner;
    }

    protected void configurePipeline() {
        wrapDocumentHandler();
        super.configurePipeline();
    }

    protected void configureXML11Pipeline() {
        wrapDocumentHandler();
        super.configureXML11Pipeline();
    }

    protected void wrapDocumentHandler() {
        XMLDocumentHandler orgHandler = getDocumentHandler();
        TeedaXMLDocumentFilterImpl filter = new TeedaXMLDocumentFilterImpl();
        filter.setDocumentHandler(orgHandler);
        setDocumentHandler(filter);
    }

}
