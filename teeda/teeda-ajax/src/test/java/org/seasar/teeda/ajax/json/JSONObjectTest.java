package org.seasar.teeda.ajax.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class JSONObjectTest extends TestCase {

    public void test1() throws Exception {
        JSONObject json = new JSONObject();
        json.put("a", "b");
        assertEquals("{\"a\":\"b\"}", json.toString());
    }

    public void test2() throws Exception {
        JSONObject json = new JSONObject();
        json.put("a", new Integer(1));
        System.out.println(json.toString());
        assertEquals("{\"a\":1}", json.toString());
    }

    public void test3() throws Exception {
        JSONObject json = new JSONObject();
        json.put("a", null);
        System.out.println(json.toString());
        assertEquals("{\"a\":null}", json.toString());
    }

    public void test4() throws Exception {
        Map map = new HashMap();
        map.put("a", "b");
        JSONObject json = new JSONObject(map);
        assertEquals("{\"a\":\"b\"}", json.toString());
    }

    public void test5() throws Exception {
        JSONObject json = new JSONObject();
        List list = new ArrayList();
        list.add("a1");
        list.add("a2");
        json.put("a", list);
        System.out.println(json.toString());
        assertEquals("{\"a\":[\"a1\",\"a2\"]}", json.toString());
    }

    public void test6() throws Exception {
        Map map = new HashMap();
        map.put("a", "b");
        Map map2 = new HashMap();
        map2.put("c", "d");
        map.put("A", map2);
        JSONObject json = new JSONObject(map);
        System.out.println(json.toString());
        assertEquals("{\"a\":\"b\",\"A\":{\"c\":\"d\"}}", json.toString());
    }

    public void test7() throws Exception {
        Map map = new HashMap();
        map.put("a", "b");
        List list = new ArrayList();
        Hoge hoge = new Hoge();
        hoge.setName("ccc");
        hoge.setValue("ddd");
        list.add(hoge);
        map.put("A", list);
        JSONObject json = new JSONObject(map);
        System.out.println(json.toString());
        assertEquals(
                "{\"a\":\"b\",\"A\":[{\"value\":\"ddd\",\"name\":\"ccc\"}]}",
                json.toString());
    }

    public void test8() throws Exception {
        Map map = new HashMap();
        map.put("a", "b");
        List list = new ArrayList();
        Hoge hoge1 = new Hoge();
        hoge1.setName("ccc");
        hoge1.setValue("ddd");
        list.add(hoge1);
        Hoge hoge2 = new Hoge();
        hoge2.setName("eee");
        hoge2.setValue("fff");
        list.add(hoge2);

        map.put("A", list);
        JSONObject json = new JSONObject(map);
        System.out.println(json.toString());
        assertEquals(
                "{\"a\":\"b\",\"A\":[{\"value\":\"ddd\",\"name\":\"ccc\"},{\"value\":\"fff\",\"name\":\"eee\"}]}",
                json.toString());
    }

    public void test9() throws Exception {
        Hoge hoge = new Hoge();
        hoge.setName("ccc");
        hoge.setValue("ddd");
        JSONObject json = new JSONObject(hoge);
        System.out.println(json.toString());
        assertEquals("{\"value\":\"ddd\",\"name\":\"ccc\"}", json.toString());
    }
    

}
