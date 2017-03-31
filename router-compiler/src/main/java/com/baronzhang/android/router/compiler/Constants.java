package com.baronzhang.android.router.compiler;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 *         2017/3/17
 */
public final class Constants {

    private static final String JAVA_LANG = "java.lang";

    public static final String INTEGER = JAVA_LANG + ".Integer";
    public static final String LONG = JAVA_LANG + ".Long";
    public static final String FLOAT = JAVA_LANG + ".Float";
    public static final String DOUBLE = JAVA_LANG + ".Double";
    public static final String SHORT = JAVA_LANG + ".Short";
    public static final String BYTE = JAVA_LANG + ".Byte";
    public static final String BOOLEAN = JAVA_LANG + ".Boolean";
    public static final String STRING = JAVA_LANG + ".String";

    public static final String PARCELABLE = "android.os.Parcelable";
    public static final String SERIALIZABLE = "java.io.Serializable";


    public final static class TYPE_KIND {
        public static final int TYPE_INTEGER = 1;
        public static final int TYPE_LONG = 2;
        public static final int TYPE_FLOAT = 3;
        public static final int TYPE_DOUBLE = 4;
        public static final int TYPE_SHORT = 5;
        public static final int TYPE_BYTE = 6;
        public static final int TYPE_BOOLEAN = 7;
        public static final int TYPE_STRING = 8;
        public static final int TYPE_PARCELABLE = 9;
        public static final int TYPE_SERIALIZABLE = 10;
        public static final int TYPE_OTHER_OBJECT = 11;
    }
}
