/// cargo build --release && cp -f target/release/rust.dll ../java/src/main/resources/win32-x86-64/
/// cargo build --release && cp -f target/release/librust.dylib ../java/src/main/resources/darwin/
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustBoolean(a: bool, b: bool) -> bool {
  a && b
}
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustByte(a: u8, b: u8) -> u8 {
  a + b
}
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustChar(a: char, b: char) -> char {
  if a > b {
    a
  } else {
    b
  }
}
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustDouble(a: f64, b: f64) -> f64 {
  a + b
}
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustFloat(a: f32, b: f32) -> f32 {
  a + b
}
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustInt(a: i32, b: i32) -> i32 {
  a + b
}
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustLong(a: i64, b: i64) -> i64 {
  a + b
}
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustShort(a: i16, b: i16) -> i16 {
  a + b
}

/// 字符串
/// 字符串
/// 字符串
/// 字符串
/// 字符串
/// 字符串
/// 字符串
/// 字符串
/// 字符串
use std::ffi::CStr;
use std::os::raw::c_char;

/// 通过 std::ffi::CStr 返回字符串
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustString(a: *const c_char, b: *const c_char) -> *const c_char {
  let (a, b) = unsafe { (CStr::from_ptr(a), CStr::from_ptr(b)) };
  let a = a.to_str().unwrap();
  let b = b.to_str().unwrap();

  let mut r = String::from(a);
  r.push_str(b);
  r.push_str("Rust返回的字符串");
  unsafe { CStr::from_bytes_with_nul_unchecked(r.as_bytes()).as_ptr() }
}
/// 只能返回固定的字面量字符串
#[no_mangle]
#[allow(non_snake_case)]
pub fn rustFixedString<'a>(a: *const c_char, b: *const c_char) -> &'a str {
  let (a, b) = unsafe { (CStr::from_ptr(a), CStr::from_ptr(b)) };
  let a = a.to_str().unwrap();
  let b = b.to_str().unwrap();

  format!("{} {}", a, b);
  "Rust返回的字符串"
}

#[cfg(test)]
mod tests {
  use super::*;

  #[test]
  fn test_rust_boolean() {
    assert_eq!(true, rustBoolean(true, true));
    assert_eq!(false, rustBoolean(true, false));
    assert_eq!(false, rustBoolean(false, true));
    assert_eq!(false, rustBoolean(false, false));
  }
  #[test]
  fn test_rust_byte() {
    assert_eq!(3, rustByte(1, 2));
  }
  #[test]
  fn test_rust_char() {
    assert_eq!('1', rustChar('1', '1'));
    assert_eq!('2', rustChar('1', '2'));
    assert_eq!('2', rustChar('2', '1'));
  }
  #[test]
  fn test_rust_double() {
    assert_eq!(3.0, rustDouble(1.0, 2.0));
  }
  #[test]
  fn test_rust_float() {
    assert_eq!(3.0, rustFloat(1.0, 2.0));
  }
  #[test]
  fn test_rust_int() {
    assert_eq!(3, rustInt(1, 2));
  }
  #[test]
  fn test_rust_long() {
    assert_eq!(3, rustLong(1, 2));
  }
  #[test]
  fn test_rust_short() {
    assert_eq!(3, rustShort(1, 2));
  }
  #[test]
  fn test_rust_string() {
    assert_eq!("12", rustString("1", "2"));
  }
}
