package org.seasar.teeda.it;

import junit.framework.Test;
import junit.framework.TestCase;

import org.seasar.teeda.unit.FileSystemTestSuiteBuilder;

public class AllHtmlTests extends TestCase {

    public static Test suite() throws Exception {
        return new FileSystemTestSuiteBuilder().build(AllHtmlTests.class);
    }

}
