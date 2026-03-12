# Lab4

## Objective

- 평문(plaintext)이 아닌 해시된 비밀번호와 레인보우 테이블을 이용해 평문인 비밀번호를 구하기

## Solution

- 상위 10대 기업이 소유하고 있는 모든 고객의 비밀번호 해시를 탈취한 상태
- 해커도 모든 고객에 포함되며, 상위 10대 기업에 회원가입한 상태
- 해커는 가입한 이메일, 평문 비밀번호를 알고 있음
- 모든 고객의 비밀번호 해시에서 해커의 비밀번호 해시를 찾기
- 해커의 평문 비밀번호에 각 해시 알고리듬을 적용해 비밀번호 해시 구하기
- 주어진 레인보우 테이블에서 비밀번호 해시를 찾아서 어떤 레인보우 테이블을 사용한지 구하기
    - Cracker 개체는 특정 한 기업이 사용한 해시 알고리듬을 구하게 됨
- 레인보우 테이블에서 O(1)의 시간복잡도로 평문을 얻을 수 있음

> 레인보우 테이블은 키-값 쌍의 목록을 가지고 있습니다. 키는 비밀번호 해시, 값은 평문 비밀번호입니다. (참고: 실제 레인보우 테이블은 디스크 용량을 줄이기 위해 추가적인 연산을 거침)

## CRC32 snippet

```java
private String computeCRC32(String input) {
    CRC32 crc32 = new CRC32();
    crc32.update(input.getBytes(StandardCharsets.UTF_8));

    return Long.toString(crc32.getValue());
}
```

- input을 byte 배열로 인코딩
    - [ref](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#getBytes-java.nio.charset.Charset-)

> 텍스트 인코딩은 UTF8를 사용하세요

- CRC32 개체 생성
- crc32.update 메소드 호출
- getValue 메소드로 해시값 구하기
- 출력은 value(long type)를 그대로 String 으로 표현해서 반환하면 됨
    - ex) 211534962 -> "211534962"

> CRC32를 제외한 모든 해시는 16진수 문자열(hex string)로 인코딩 되어 있습니다

## MD2, MD5, SHA-1, SHA-256 snippet

```java
private String computeOtherHash(String input, HashAlgorithm algorithm) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance(algorithm.getName());
    byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

    return bytesToHexString(digest);
}

private String bytesToHexString(byte[] bytes) {
    boolean isPrefix = true;
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
        if (b == 0 && isPrefix) {
            continue;
        }
        if (b != 0) {
            isPrefix = false;
        }
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
}
```

- input을 byte 배열로 인코딩
- 각 알고리듬에 맞는 MessageDigest 개체 생성
- 해시값 구하기
    - [ref](https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html#digest-byte:A-)

- 출력은 해시값을 hex string으로 인코딩해서 반환
    - prefix zero trimming 필요

> CRC32를 제외한 모든 해시는 16진수 문자열(hex string)로 인코딩 되어 있습니다.
> 앞에 0x가 붙지 않습니다.
> 알파벳은 소문자를 사용합니다.
> 인코딩의 입력으로 사용하는 바이트 배열에서 결과에 영향을 주지 않는 바이트는 무시합니다. (예: 00 00 00 0d는 0d로 인코딩)
