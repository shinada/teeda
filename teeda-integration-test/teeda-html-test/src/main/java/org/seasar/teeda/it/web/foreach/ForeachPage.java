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
package org.seasar.teeda.it.web.foreach;

public class ForeachPage {

	private String foo;

	private String bar;

	private FooItem[] aaaItems;

	public int aaaIndex;

	public String initialize() {
		System.out.println("<initialize>foo:" + foo);
		System.out.println("<initialize>bar:" + bar);
		return null;
	}

	public String prerender() {
		System.out.println("<prerender>foo:" + foo);
		System.out.println("<prerender>bar:" + bar);
		return null;
	}

	public String doForeach() {
		System.out.println("<doForEach>foo:" + foo);
		System.out.println("<doForEach>bar:" + bar);
		return null;
	}

	public Class doForeachConfirm() {
		System.out.println("<doForeachConfirm>foo:" + foo);
		System.out.println("<doForeachConfirm>bar:" + bar);
		return ForeachConfirmPage.class;
	}

	public FooItem[] getAaaItems() {
		if (aaaItems == null) {
			aaaItems = new FooItem[3];
			aaaItems[0] = createItem("a1", "b1");
			aaaItems[1] = createItem("a2", "b2");
			aaaItems[2] = createItem("a3", "b3");
		}
		return aaaItems;
	}

	private FooItem createItem(String foo, String bar) {
		final FooItem item = new FooItem();
		item.setFoo(foo);
		item.setBar(bar);
		return item;
	}

	public void setAaaItems(FooItem[] fooItems) {
		this.aaaItems = fooItems;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public String getFooStyle() {
		return "background-color:yellow";
	}

	public String getRowStyleClass() {
		return (aaaIndex + 1) % 2 == 0 ? "even" : "odd";
	}

	public static class FooItem {

		private String foo;

		private String bar;

		public String getBar() {
			return bar;
		}

		public void setBar(String bar) {
			this.bar = bar;
		}

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}
	}

}
