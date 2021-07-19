package org.example.jna;

import com.sun.jna.Native;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RustNativeTest {
    @BeforeClass
    public static void init() {
        Native.register(RustNative.class, "rust");
    }

    @Test
    public void testRustBoolean() {
        Assert.assertEquals(true, RustNative.rustBoolean(true, true));
        Assert.assertEquals(false, RustNative.rustBoolean(true, false));
        Assert.assertEquals(false, RustNative.rustBoolean(false, true));
        Assert.assertEquals(false, RustNative.rustBoolean(false, false));
    }

    @Test
    public void testRustByte() {
        Assert.assertEquals((byte) 3, RustNative.rustByte((byte) 1, (byte) 2));
    }

    @Test
    public void testRustChar() {
        Assert.assertEquals('1', RustNative.rustChar('1', '1'));
        Assert.assertEquals('2', RustNative.rustChar('1', '2'));
        Assert.assertEquals('2', RustNative.rustChar('2', '1'));
    }

    @Test
    public void testRustDouble() {
        Assert.assertEquals(3D, RustNative.rustDouble(1D, 2D), 0.001D);
    }

    @Test
    public void testRustFloat() {
        Assert.assertEquals(3F, RustNative.rustFloat(1F, 2F), 0.001F);
    }

    @Test
    public void testRustInt() {
        Assert.assertEquals(3, RustNative.rustInt(1, 2));
    }

    @Test
    public void testRustLong() {
        Assert.assertEquals(3L, RustNative.rustLong(1L, 2L));
    }

    @Test
    public void testRustShort() {
        Assert.assertEquals((short) 3, RustNative.rustShort((short) 1, (short) 2));
    }

    @Test
    public void testRustString() {
        String a = "Java输入的字符串";
        String b = "2";
        String fixed = "Rust返回的字符串";
        String expected = a + b + fixed;
        Assert.assertEquals(expected, RustNative.rustString(a, b));
    }

    @Test
    public void testRustFixedString() {
        Assert.assertEquals("Rust返回的字符串", RustNative.rustFixedString("1", "2"));
    }

}
