package org.seasar.teeda.extension.spike.rhino;


public class FooTest extends RhinoTestCase {

    public void test() throws Exception {
        try {
            createJsTestCase();
        } catch (Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}
