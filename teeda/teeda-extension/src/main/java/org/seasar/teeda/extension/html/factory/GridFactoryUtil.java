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
package org.seasar.teeda.extension.html.factory;

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.extension.ExtensionConstants;
import org.seasar.teeda.extension.html.ActionDesc;
import org.seasar.teeda.extension.html.ElementNode;
import org.seasar.teeda.extension.html.PageDesc;

/**
 * @author manhole
 */
public class GridFactoryUtil {

    private static final String GRID = "Grid";

    private static final String GRID_X = "GridX";

    private static final String GRID_Y = "GridY";

    private static final String GRID_XY = "GridXY";

    static String getNaturalName(final String id) {
        final int pos = id.lastIndexOf(GridFactoryUtil.GRID);
        return id.substring(0, pos);
    }

    static boolean isMatchGrid(ElementNode elementNode, PageDesc pageDesc,
            ActionDesc actionDesc) {
        if (pageDesc == null) {
            return false;
        }
        if (!JsfConstants.TABLE_ELEM.equalsIgnoreCase(elementNode.getTagName())) {
            return false;
        }
        final String id = elementNode.getId();
        if (id == null) {
            return false;
        }
        if (id.endsWith(GridFactoryUtil.GRID)
                || id.endsWith(GridFactoryUtil.GRID_X)
                || id.endsWith(GridFactoryUtil.GRID_Y)
                || id.endsWith(GridFactoryUtil.GRID_XY)) {
        } else {
            return false;
        }
        final String itemsName = getItemsName(id);
        if (pageDesc.hasItemsProperty(itemsName)) {
            return true;
        }
        return false;
    }

    static String getItemsName(final String id) {
        final String naturalName = getNaturalName(id);
        final String itemsName = naturalName + ExtensionConstants.ITEMS_SUFFIX;
        return itemsName;
    }

    static boolean isGridChild(ElementNode elementNode, PageDesc pageDesc,
            ActionDesc actionDesc) {
        final ElementNode gridNode = findParentGridNode(elementNode, pageDesc,
                actionDesc);
        if (gridNode != null) {
            return true;
        }
        return false;
    }

    static ElementNode findParentGridNode(ElementNode elementNode, PageDesc pageDesc,
            ActionDesc actionDesc) {
        for (ElementNode node = elementNode.getParent(); node != null; node = node
                .getParent()) {
            if (isMatchGrid(node, pageDesc, actionDesc)) {
                return node;
            }
        }
        return null;
    }

}