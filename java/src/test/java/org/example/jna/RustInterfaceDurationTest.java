package org.example.jna;

import org.example.utils.Sustain;
import org.junit.Test;

import java.time.Duration;

public class RustInterfaceDurationTest {

    RustInterfaceTest rustNativeTest = new RustInterfaceTest();

    @Test
    public void testRustBoolean() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustBoolean());
    }

    @Test
    public void testRustByte() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustByte());
    }

    @Test
    public void testRustChar() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustChar());
    }

    @Test
    public void testRustDouble() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustDouble());
    }

    @Test
    public void testRustFloat() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustFloat());
    }

    @Test
    public void testRustInt() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustInt());
    }

    @Test
    public void testRustLong() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustLong());
    }

    @Test
    public void testRustShort() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustShort());
    }

    @Test
    public void testRustString() {
        // 内存泄漏
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustString());
    }

    @Test
    public void testRustFixedString() {
        Sustain.run(Duration.ofMinutes(10L), () -> rustNativeTest.testRustFixedString());
    }

}
