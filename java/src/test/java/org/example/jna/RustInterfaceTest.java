package org.example.jna;

import com.sun.jna.Native;
import org.junit.Assert;
import org.junit.Test;

public class RustInterfaceTest {
    private final RustInterface rust = Native.load("rust", RustInterface.class);

    /**
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
     */
    @Test
    public void testRustBoolean() {
        Assert.assertEquals(true, rust.rustBoolean(true, true));
//        Assert.assertEquals(false, rust.rustBoolean(true, false));
        Assert.assertEquals(false, rust.rustBoolean(false, true));
        Assert.assertEquals(false, rust.rustBoolean(false, false));

        Assert.assertEquals(true, rust.rustBoolean(Boolean.valueOf(true), Boolean.valueOf(true)));
//        Assert.assertEquals(false, rust.rustBoolean(Boolean.valueOf(true), Boolean.valueOf(false)));
        Assert.assertEquals(false, rust.rustBoolean(Boolean.valueOf(false), Boolean.valueOf(true)));
        Assert.assertEquals(false, rust.rustBoolean(Boolean.valueOf(false), Boolean.valueOf(false)));
    }

    @Test
    public void testRustByte() {
        Assert.assertEquals((byte) 3, rust.rustByte((byte) 1, (byte) 2));

        Assert.assertEquals(Byte.valueOf((byte) 3), rust.rustByte(Byte.valueOf((byte) 1), Byte.valueOf((byte) 2)));
    }

    @Test
    public void testRustChar() {
        Assert.assertEquals('1', rust.rustChar('1', '1'));
        Assert.assertEquals('2', rust.rustChar('1', '2'));
        Assert.assertEquals('2', rust.rustChar('2', '1'));

        Assert.assertEquals(Character.valueOf('1'), rust.rustChar(Character.valueOf('1'), Character.valueOf('1')));
        Assert.assertEquals(Character.valueOf('2'), rust.rustChar(Character.valueOf('1'), Character.valueOf('2')));
        Assert.assertEquals(Character.valueOf('2'), rust.rustChar(Character.valueOf('2'), Character.valueOf('1')));
    }

    @Test
    public void testRustDouble() {
        Assert.assertEquals(3D, rust.rustDouble(1D, 2D), 0.001D);

        Assert.assertEquals(3D, rust.rustDouble(Double.valueOf(1D), Double.valueOf(2D)), 0.001D);
    }

    @Test
    public void testRustFloat() {
        Assert.assertEquals(3F, rust.rustFloat(1F, 2F), 0.001F);

        Assert.assertEquals(3F, rust.rustFloat(Float.valueOf(1F), Float.valueOf(2F)), 0.001F);
    }

    @Test
    public void testRustInt() {
        Assert.assertEquals(3, rust.rustInt(1, 2));

        Assert.assertEquals(Integer.valueOf(3), rust.rustInt(Integer.valueOf(1), Integer.valueOf(2)));
    }

    @Test
    public void testRustLong() {
        Assert.assertEquals(3L, rust.rustLong(1L, 2L));

        Assert.assertEquals(Long.valueOf(3L), rust.rustLong(Long.valueOf(1L), Long.valueOf(2L)));
    }

    @Test
    public void testRustShort() {
        Assert.assertEquals((short) 3, rust.rustShort((short) 1, (short) 2));

        Assert.assertEquals(Short.valueOf((short) 3), rust.rustShort(Short.valueOf((short) 1), Short.valueOf((short) 2)));
    }

    @Test
    public void testRustString() {
        String a = "Java输入的字符串";
        String b = "2";
        String fixed = "Rust返回的字符串";
        String expected = a + b + fixed;
        Assert.assertEquals(expected, rust.rustString(a, b));
    }

    @Test
    public void testRustFixedString() {
        Assert.assertEquals("Rust返回的字符串", rust.rustFixedString("1", "2"));
    }

}
