package org.example.authentication;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class JWTHelper {

  public static PublicKey getPublicSigningKey() throws Exception {
    byte[] keyBytes = StreamUtils
        .copyToByteArray(new ClassPathResource("keystore/jwt_public_key.der").getInputStream());
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(spec);
  }
}