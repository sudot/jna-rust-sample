package org.example.jna;

import com.sun.jna.Library;

/**
 * 演示接口（interface-mapping）
 * <p>
 * direct-mapping 对于基本类型（包括 Pointer）性能更好，interface-mapping 在复杂类型上略优。
 *
 * <pre>
 * 遇到一个神奇的事，在 windows 下使用 interface 方式调用 rustBoolean 入参是 a = true, b = false 的情况下：
 * （windows native 调用方式以及 macos native 和 interface 均无此差异现象，所有情况均符合预期）
 *
 * 以下方式返回 true，不符合预期
 * #[no_mangle]
 * #[allow(non_snake_case)]
 * pub fn rustBoolean(a: bool, b: bool) -> bool {
 *   a && b
 * }
 *
 * 以下方式返回 false，符合预期
 * #[no_mangle]
 * #[allow(non_snake_case)]
 * pub fn rustBoolean(a: bool, b: bool) -> bool {
 *   println!("{}:{}", a, b);
 *   a && b
 * }
 *
 * 以下方式返回 true，不符合预期
 * #[no_mangle]
 * #[allow(non_snake_case)]
 * pub fn rustBoolean(a: bool, b: bool) -> bool {
 *   let c = a && b
 *   println!("{}:{}", a, b);
 *   c
 * }
 *
 * 以下方式返回 false，符合预期
 * #[no_mangle]
 * #[allow(non_snake_case)]
 * pub fn rustBoolean(a: bool, b: bool) -> bool {
 *   let c = a && b
 *   println!("{}", c);
 *   c
 * }
 * </pre>
 * @author tangjialin on 2021-09-19.
 */
public interface RustInterface extends Library {
    boolean rustBoolean(boolean a, boolean b);
    byte rustByte(byte a, byte b);
    char rustChar(char a, char b);
    double rustDouble(double a, double b);
    float rustFloat(float a, float b);
    int rustInt(int a, int b);
    long rustLong(long a, long b);
    short rustShort(short a, short b);

    Boolean rustBoolean(Boolean a, Boolean b);
    Byte rustByte(Byte a, Byte b);
    Character rustChar(Character a, Character b);
    Double rustDouble(Double a, Double b);
    Float rustFloat(Float a, Float b);
    Integer rustInt(Integer a, Integer b);
    Long rustLong(Long a, Long b);
    Short rustShort(Short a, Short b);

    String rustString(String a, String b);
    String rustFixedString(String a, String b);
}
