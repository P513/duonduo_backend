package com.gg.duonduo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

import static com.auth0.jwt.JWT.require;
@Service
public class JwtToken {
    @Value("${JWT.ISSUER}")
    private String ISSUER;
    @Value("${JWT.SECRET}")
    private String SECRET;

    public String createToken(final long userIdx){
        try{
            JWTCreator.Builder b = JWT.create();
            b.withIssuer(ISSUER);
            b.withClaim("userIdx",userIdx);
            b.withExpiresAt(expiresAt());
            return b.sign(Algorithm.HMAC256(SECRET));
        }catch(JWTCreationException jwtCreationException) {

        }
        return null;
    }

    private Date expiresAt()  {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 1시간
        cal.add(Calendar.HOUR, 1);
        return cal.getTime();
    }

    public static class TokenRes {
        private String token;

        public TokenRes() {

        }

        public TokenRes(final String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public Long decode(final String token){
        try {
            // 토큰 검증
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build().verify(token);
            // 토큰 payload 반환, 정상적인 토큰이라면 토큰 사용자 고유 ID, 아니라면 -1
            return (decodedJWT.getClaim("userIdx").asLong().longValue());
        } catch (JWTVerificationException jve) {
        } catch (Exception e) {
        }
        return null;
    }
}
