package kr.co.ihm.wedding.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import kr.co.ihm.wedding.common.ServiceConstant;
import kr.co.ihm.wedding.common.exception.InternalServerErrorException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Map;


@Component
public class Tokenizer {
    
    private static final Logger logger = LoggerFactory.getLogger(Tokenizer.class);

    private final static String KEY_ALGORITHM = "RSA";
    private final static String KEY_USER_ID = "userId";
    private final static String KEY_PTN_USER_ID = "ptnUserId";
    private final static String KEY_PTN_ID = "ptnId";
    private final static String KEY_PTN_NM = "ptnNm";
    private final static String KEY_USER_DIV_CD = "userDivCd";
    private static String PRV_KEY;
    private static String PUB_KEY;

//    @Value("${jwt.private.key}")
//    private void setPrvKeyValue(String key) {
//        PRV_KEY = key;
//    }
//
//    @Value("${jwt.public.key}")
//    private void setPubKeyValue(String key) {
//        PUB_KEY = key;
//    }

    /**
     * JWT 토큰 발급
     * @param <T>
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws JOSEException
     */
    @SuppressWarnings("unchecked")
    public static <T> String issueToken(T data) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {

        String jwtToken = "";
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            Map<String, Object> dataMap = objectMapper.convertValue(data, Map.class);

            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(PUB_KEY)));

            // Header & Payload
            JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM);
            Payload payload = new Payload(dataMap);
            JWEObject jweObject = new JWEObject(header, payload);

            // Encrypt
            JWEEncrypter encrypter = new RSAEncrypter(pubKey);
            jweObject.encrypt(encrypter);

            jwtToken = jweObject.serialize();

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("NoSuchAlgorithmException : " + e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException("InvalidKeySpecException : " + e.getMessage());
        } catch (JOSEException e) {
            throw new JOSEException("JOSEException : " + e.getMessage());
        }

        return jwtToken;
    }

    /**
     * JWT 토큰 유효성 확인
     * @param jwtToken
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    public static boolean verify(String jwtToken) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {

        if (null == jwtToken || "".equals(jwtToken)) {
            return false;
        }

        try {

            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            RSAPrivateKey prvKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(PRV_KEY)));

            // Parse JWT Token
            JWEObject jweObject = JWEObject.parse(jwtToken);
            JWEDecrypter decrypter = new RSADecrypter(prvKey);
            jweObject.decrypt(decrypter);

            Map<String, Object> dataMap = jweObject.getPayload().toJSONObject();

            if (null != dataMap) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("NoSuchAlgorithmException : " + e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException("InvalidKeySpecException : " + e.getMessage());
        } catch (ParseException e) {
            throw new ParseException("ParseException : " + e.getMessage(), 0);
        } catch (JOSEException e) {
            throw new JOSEException("JOSEException : " + e.getMessage());
        }

        return false;
    }

    /**
     * JWT Payload get
     * @param key
     * @param jwtToken
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    public static Object get(String key, String jwtToken) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {

        Object result = null;

        try {
            
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            RSAPrivateKey prvKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(PRV_KEY)));

            // Parse JWT Token
            JWEObject jweObject = JWEObject.parse(jwtToken);
            JWEDecrypter decrypter = new RSADecrypter(prvKey);
            jweObject.decrypt(decrypter);

            Map<String, Object> dataMap = jweObject.getPayload().toJSONObject();
            result = dataMap.get(key);

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("NoSuchAlgorithmException : " + e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException("InvalidKeySpecException : " + e.getMessage());
        } catch (ParseException e) {
            throw new ParseException("ParseException : " + e.getMessage(), 0);
        } catch (JOSEException e) {
            throw new JOSEException("JOSEException : " + e.getMessage());
        }

        return result;
    }

    /**
     * 사용자 ID 반환
     * @param request
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    public static String getUserId(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        return getUserId(request, KEY_USER_ID);
    }


    /**
     * 파트너 사용자 ID 반환
     * @param request
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    public static String getPtnUserId(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        return getUserId(request, KEY_PTN_USER_ID);
    }
    
    /**
     * 파트너 ID 반환
     * @param request
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    public static String getPtnId(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        return getUserId(request, KEY_PTN_ID);
    }

    /**
     * 파트너 사용자 ID 반환
     * @param request
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    public static String getUserDivCd(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        return getUserId(request, KEY_USER_DIV_CD);
    }


    /**
     * 파트너 명 반환
     * @param request
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    public static String getPtnNm(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        return getUserId(request, KEY_PTN_NM);
    }

    /**
     * 사용자 ID 반환
     * @param request
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws ParseException
     * @throws JOSEException
     */
    private static String getUserId(HttpServletRequest request, String key) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        
        String jwtToken = getToken(request);
        
        if (jwtToken == null || jwtToken == "") {
            jwtToken = "Bearer eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.eIV6XIK3qQUnD62Qb1vnZx-LSxOS1Mx7N85DUOZaXRBh0YtzRg5DunCPaPtTb-6mBVBo8xtZnKYOdFkZnTqVDmJDAZcbh5JKKv2AozyHp18tq7Qmf6xmiaJ1M9WlbyxzOoxjIITKDvnyoIg0gGXEQw3Bm_StHttnRGv9QAXjqoOMiwcp-2GHDnt-MqAwcFmPi2plf3UdRpcvF3NqcHofalOXs-9q86U0V0BJ9J70lT4Ke3UL6zsaz7_uXmf9MS6VShQTiiFvOkmRD-OnPYi3COHlbI9t4pYbP-JaelLYGOnivZq631BVsUHFqK33nC9pOE8KrnY_Fo6cMuPRv2XDhw.TAtp-6nqN4xzZnGN.mhsYDQPQV-ef0cfDezZYn8lVMkH1lME8r5eaXLYu3-z06N3E3cy3QWIPr25EmGnkZ4sDGFLWG4tZ1IKgvy1HTdwWUSy1aGQO1yKEMxjn18mGjbL4F-uHTGLZAHZAs1NtJk0adTKZU6ELDfklGsKEeNeh_19jrrAEc-oNGykj3kUfLuM.UqS7lzcIyg4wdFrAKQGN2A";
        }

        if (StringUtil.isEmpty(jwtToken)) {             // 토큰 빈문자 검사
            throw new InternalServerErrorException(ServiceConstant.ResponseMessage.ERR_AUTH_012);       // Does not exist token
        } else if (!jwtToken.startsWith("Bearer ")) {   // Bearer Scheme 검사
            throw new InternalServerErrorException(ServiceConstant.ResponseMessage.ERR_AUTH_015);       // Not Bearer scheme
        } else {

            jwtToken = jwtToken.replaceAll("Bearer ", "");
    
            // 정상 토큰 검사
            if (!verify(jwtToken)) {
                throw new InternalServerErrorException(ServiceConstant.ResponseMessage.ERR_AUTH_013);
            }
        }        
        
        return String.valueOf(get(key, jwtToken));
    }

    /**
     * 헤더 인증토큰 반환
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        return request.getHeader(ServiceConstant.Keys.AUTHORIZATION);
    }

    /**
     * 암호화 키 생성 (키 생성때만 사용)
     * @throws NoSuchAlgorithmException
     */
    @SuppressWarnings("unused")
    private void generateKeyPair() throws NoSuchAlgorithmException {

        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(2048, new SecureRandom());
            
            final KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAPublicKey pubKey = (RSAPublicKey)keyPair.getPublic();
            RSAPrivateKey prvKey = (RSAPrivateKey)keyPair.getPrivate();

            logger.info("RSAPublicKey: {}", Base64.encodeBase64String(pubKey.getEncoded()));
            logger.info("RSAPrivateKey: {}", Base64.encodeBase64String(prvKey.getEncoded()));

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("NoSuchAlgorithmException : " + e.getMessage());
        }
    }
}
