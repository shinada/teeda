/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package examples.teeda.web.foreach;

import java.util.Arrays;
import java.util.List;

public class ForeachStringListPage {

    private String aaa;

    private List aaaItems;

    public List getAaaItems() {
        if (aaaItems == null) {
            aaaItems = Arrays.asList(new String[] { "D", "C", "B", "A" });
        }
        return aaaItems;
    }

    public void setAaaItems(List aaaItems) {
        this.aaaItems = aaaItems;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

}
