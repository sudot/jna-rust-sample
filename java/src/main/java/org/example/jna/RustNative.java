package org.example.jna;

/**
 * 演示接口（interface-mapping）
 * <p>
 * direct-mapping 对于基本类型（包括 Pointer）性能更好，interface-mapping 在复杂类型上略优。
 *
 * @author tangjialin on 2021-09-19.
 */
public class RustNative {
    public static native boolean rustBoolean(boolean a, boolean b);
    public static native byte rustByte(byte a, byte b);
    public static native char rustChar(char a, char b);
    public static native double rustDouble(double a, double b);
    public static native float rustFloat(float a, float b);
    public static native int rustInt(int a, int b);
    public static native long rustLong(long a, long b);
    public static native short rustShort(short a, short b);

    // java.lang.IllegalArgumentException: class java.lang.Boolean is not a supported return type (in method nativeBoolean in class org.example.jna.RustNative)
    // java.lang.IllegalArgumentException: class java.lang.Boolean is not a supported argument type (in method nativeBoolean in class org.example.jna.RustNative)
    // public static native Boolean rustBoolean(Boolean a, Boolean b);
    // public static native Byte rustByte(Byte a, Byte b);
    // public static native Character rustChar(Character a, Character b);
    // public static native Double rustDouble(Double a, Double b);
    // public static native Float rustFloat(Float a, Float b);
    // public static native Integer rustInt(Integer a, Integer b);
    // public static native Long rustLong(Long a, Long b);
    // public static native Short rustShort(Short a, Short b);

    public static native String rustString(String a, String b);
    public static native String rustFixedString(String a, String b);
}
