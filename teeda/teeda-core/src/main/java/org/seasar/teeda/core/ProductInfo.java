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
package org.seasar.teeda.core;

/**
 * @author shot
 * @author manhole
 */
public class ProductInfo {

    private static final String MAJOR_VERSION = "1";

    private static final String MINOR_VERSION = "0";

    private static final String LOCAL_VERSION = "4";

    private static boolean isSnapshot = true;

    public static String getProductName() {
        return "Teeda";
    }

    public static String getVersion() {
        return MAJOR_VERSION + "." + MINOR_VERSION + "." + LOCAL_VERSION
                + ((isSnapshot) ? "-SNAPSHOT" : "");
    }

    /*
     * MANIFEST.MFのmain-classに指定しておき、jarを実行するとバージョン番号が
     * コンソールへ出力されるようにする。
     */
    public static void main(final String[] args) {
        System.out.println("teeda.version: " + getVersion());
    }

}
