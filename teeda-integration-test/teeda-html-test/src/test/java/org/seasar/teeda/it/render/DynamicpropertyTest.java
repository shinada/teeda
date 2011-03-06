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
package org.seasar.teeda.it.render;

import junit.framework.Test;

import org.seasar.teeda.unit.web.TeedaWebTestCase;
import org.seasar.teeda.unit.web.TeedaWebTester;

/**
 * @author manhole
 */
public class DynamicpropertyTest extends TeedaWebTestCase {

	public static Test suite() throws Exception {
		return setUpTest(DynamicpropertyTest.class);
	}

	/*
	 * "aaa": getAaaStyle "aaa-header": getAaaHeaderStyle "aaa-footer":
	 * Pageにプロパティが無いのでhtmlに書かれたまま
	 */
	public void testRender() throws Exception {
		// ## Arrange ##
		TeedaWebTester tester = new TeedaWebTester();

		// ## Act ##
		// ## Assert ##
		tester.beginAt(getBaseUrl(),
				"view/dynamicproperty/dynamicproperty.html");
		tester.dumpHtml();
		tester.assertAttributeEqualsById("aaa-header", "style",
				"background-color:yellow");
		tester.assertAttributeEqualsById("aaa", "style",
				"background-color:blue");
		tester.assertAttributeEqualsById("aaa-footer", "style",
				"background-color:green");
		tester.assertAttributeEqualsById("bbb-1", "href", "Dynamic2");

		tester.submitById("goDynamicproperty2");
		tester.dumpHtml();
		tester.assertAttributeEqualsById("foo", "src", "/foo.gif");
		tester.assertAttributeEqualsById("bar", "href", "/bar.css");
		tester.assertAttributeEqualsById("baz", "src", "/js/baz.js");
		tester.assertAttributeEqualsById("hoge", "src", "/hoge.gif");
	}

}
