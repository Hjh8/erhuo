package Utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;

/**
 * 非对称加密RSA256算法 生成 Token
 **/
public class Token {

    // 过期时间6h
    private static final long EXPIRE_TIME = 1000 * 6 * 3600;
    private static RSAKey rsaKey;
    private static RSAKey publicRsaKey;

    static {
        /**
         * 生成公钥，公钥是提供出去，让使用者校验token的签名
         */
        try {
            rsaKey = new RSAKeyGenerator(2048).generate();
            publicRsaKey = rsaKey.toPublicJWK();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据用户的id生成token
     * */
    public static String buildToken(String id) {
        try {
            // 1. 生成秘钥,秘钥是token的签名方持有，不可对外泄漏
            RSASSASigner secretSigner = new RSASSASigner(rsaKey);

            // 2. 建立payload 载体
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("Web")
                    .issuer("47.115.46.57")
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .claim("id", id)
                    .build();

            // 3. 建立签名
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
            signedJWT.sign(secretSigner);

            // 4. 生成token
            String token = signedJWT.serialize();
            return token;

        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证Token
     * */
    public static int verifyToken(String token) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            // 添加私密钥匙，进行解密
            RSASSAVerifier secretSigner = new RSASSAVerifier(publicRsaKey);
            // 校验是否有效
            if (!jwt.verify(secretSigner)) {
                return -1;
            }
            // 校验超时
            if (new Date().after(jwt.getJWTClaimsSet().getExpirationTime())) {
                return 0;
            }
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
