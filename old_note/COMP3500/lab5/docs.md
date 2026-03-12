# Lab5

## isPrime

- [miller–rabin_primality_test](https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test)

> When the number n to be tested is small, trying all a < 2(ln n)2 is not necessary, as much smaller sets of potential
> witnesses are known to suffice. For example, Pomerance, Selfridge, Wagstaff[4] and Jaeschke[12] have verified that
> if n < 2,047, it is enough to test a = 2;
> if n < 1,373,653, it is enough to test a = 2 and 3;
> if n < 9,080,191, it is enough to test a = 31 and 73;
> if n < 25,326,001, it is enough to test a = 2, 3, and 5;
> if n < 3,215,031,751, it is enough to test a = 2, 3, 5, and 7;
> if n < 4,759,123,141, it is enough to test a = 2, 7, and 61;
> if n < 1,122,004,669,633, it is enough to test a = 2, 13, 23, and 1662803;
> if n < 2,152,302,898,747, it is enough to test a = 2, 3, 5, 7, and 11;
> if n < 3,474,749,660,383, it is enough to test a = 2, 3, 5, 7, 11, and 13;
> if n < 341,550,071,728,321, it is enough to test a = 2, 3, 5, 7, 11, 13, and 17.

- Based on the above citation, we can fix the list of bases.

- Implement pseudo code provided by Wikipedia

### How to Decide 'k'

- In the Miller–Rabin test, each iteration decreases the probability of a composite number being falsely classified as
  prime by a factor of at most 1/4. That is, if one iteration has an error probability of at most 1/4, then after k
  iterations, the overall error probability is at most  (1/4)^k.
- In practice, many implementations use **k around 10 to 20**, which is sufficient for most applications.

## Decentralized Finance(DeFi)

- In a decentralized finance system, Personal information including individual account balance is not stored in
  centralized database that contains personal data. Instead, balances are recorded on distributed ledger(the blockchain)
  where each account is identified solely by public key(address derived from account).
- In this laboratory use hashmap to simulate Defi system.
    - In a real defi system, account state is stored on the blockchain and maintained through consensus.

- `getBalance` function is implemented by hashmap

### Only the owner of the wallet holds the corresponding private key

> 이 은행에서 어떤 개인의 지갑(계좌)을 나타내는 식별자는 RSA 공개(public) 키이며, 그 외에 실명, 주소, 전화 등의 어떤 개인정보도 저장하지 않습니다. 그 공개 키를 아는 사람이면 누구나 잔액을
> 자유로이 볼 수 있습니다. 하지만 그래도 상관이 없죠! 그 지갑의 소유자가 누군지 아무도 모르거든요. :P

### Transfer failure cases

1. invalid amount:
    - amount <= 0
    - sender has insufficient balance
    - receiver balance overflow
        - java doesn't have unsigned long type
2. invalid signature:
    - using a public key that does not match the private key that was used to make signature.
    - see also [decrypt signature by public key](#decrypt-signature-by-public-key)
3. incorrect signature:
    - compare byte array(content-based)
4. no sender found
5. no receiver found
    - lazy account creation
        - Only when funds are sent(sender exists and transaction occurs), the account is added to blockchain.

### Combine from, to, amount

- concat byte arrays.
    - sequentially(from left to right)

### convert long to String

- concat byte by byte
    - from left to right
- I got a hint from 'decodeFromHexString' function.
- [apply SHA-256 hash](https://github.com/satanmoo/Snippet/blob/main/java/SHA_256Hashing.java)

```java
private static byte[] decodeFromHexString(String hexString) {
    byte[] bytes = new byte[hexString.length() / 2];
    for (int i = 0; i < hexString.length(); i += 2) {
        int firstDigit = Character.digit(hexString.charAt(i), 16);
        int secondDigit = Character.digit(hexString.charAt(i + 1), 16);
        bytes[i / 2] = (byte) ((firstDigit << 4) + secondDigit);
    }
    return bytes;
}

```

```java
private byte[] longToBytes(final long value) {
    byte[] bytes = new byte[Long.BYTES];
    for (int i = 0; i < Long.BYTES; i++) {
        bytes[0] = (byte) (value >> (Byte.SIZE * (Long.BYTES - 1 - i)));
    }

    return bytes;
}
```

### decrypt signature by public key

- If 'decrypt' method throws runtime exception, it indicates that the signature is invalid.

- [decryption](https://github.com/satanmoo/Snippet/blob/main/java/RsaDecryptUsingPublicKey.java)
- [exceptions](https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html#doFinal-byte:A-)
